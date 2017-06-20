package controller;

import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Group;
import model.Message;
import model.ServerInfo;

public class ClientConnection extends Thread{

	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Group group;
	private boolean stopThread = false;

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public ClientConnection(Socket socket)
	{
		this.socket = socket;
		try
		{
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null,"Erro ao conectar com o cliente:  "+ e.getMessage(),"Erro!",JOptionPane.WARNING_MESSAGE);
			ServerInfo.getClientes().remove(this);
			this.interrupt();
		}
		
	}

	public void send(Message msg)
	{
		try
		{
			output.writeObject(msg);
		}
		catch (IOException e)
		{
			System.out.println("Conexão com o cliente perdida, fechando conexão.");
			ServerInfo.getClientes().remove(this);
			//this.interrupt();
			stopThread = true;
		}		
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			if (stopThread){
				return;
			}
			try
			{
				Message m = (Message) input.readObject();
				if (m == null){
					continue;
				}
				
				if (m.getStatus() == Message.MessageType.REQUEST_GROUPS){
					Message me = new Message();
					me.setAllGroups(ServerInfo.getGroups());
					me.setStatus(Message.MessageType.GET_GROUPS);
					send(me);
				}
				else if (m.getStatus() == Message.MessageType.GROUP_JOIN){
					group = m.getCurrentGroup();
					Message me = new Message();
					me.setStatus(Message.MessageType.EMPTY);
					send(me);
				}
				else if (m.getStatus() == Message.MessageType.TEXT_MESSAGE){
					if (group != null){
						ArrayList<ClientConnection> cli = ServerInfo.getClientes();
						
						m.setStatus(Message.MessageType.RECEIVE_MESSAGE);
						
						for(ClientConnection c : cli){
							if (c.getGroup().getGroupID() == group.getGroupID()){
								c.send(m);
							}
						}
					}
				}
				else{
					Message me = new Message();
					me.setStatus(Message.MessageType.EMPTY);
					send(me);
				}
				output.flush();
				output.reset();
			}
			catch (ClassNotFoundException | IOException e)
			{
				System.out.println("Conexão com o cliente perdida, fechando conexão.");
				ServerInfo.getClientes().remove(this);
				return;
			}
		}
	}
	
}
