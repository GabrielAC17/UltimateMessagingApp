package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import model.ClientInfo;
import model.Group;
import model.Message;

public class Client extends Thread
{
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	public boolean isConnected = false;
	
	public Client(String connection, int port)
	{
		try
		{
			socket = new Socket(connection, port);
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
			isConnected = true;
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null,"Erro ao conectar ao servidor: "+ e.getMessage(),"Erro!",JOptionPane.WARNING_MESSAGE);
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
			JOptionPane.showMessageDialog(null,"Erro ao enviar mensagem: "+ e.getMessage(),"Erro!",JOptionPane.WARNING_MESSAGE);
			
		}		
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			try
			{
				Message m = (Message) input.readObject();
				if (m == null){
					
					continue;
				}
				if (m.getStatus() == Message.MessageType.GET_GROUPS){
					if (m.getAllGroups() != null){
						ClientInfo.setGroups(m.getAllGroups());
						Main.setListData();
					}
					Message me = new Message();
					me.setStatus(Message.MessageType.EMPTY);
					send(me);
				}
				else if (m.getStatus() == Message.MessageType.RECEIVE_MESSAGE){
					Main.addMessage(m);
				}
				
			}
			catch (ClassNotFoundException | IOException e)
			{
				JOptionPane.showMessageDialog(null,"Erro ao receber mensagem do servidor: "+ e.getMessage(),"Erro!",JOptionPane.WARNING_MESSAGE);
				Main.resetUI();
				return;
			}
		}
	}
}
