 package controller;

import model.ClientInfo;
import model.Group;
import model.Message;
import view.ViewConnection;
import view.ViewMessages;

public class Main {
	private static Client client;
	private static ViewConnection mainWindow;
	private static ViewMessages window;
	
	public static void main(String[] args) {
		mainWindow = new ViewConnection();
		window = new ViewMessages();
		mainWindow.getFrame().setVisible(true);
		window.getFrame().setVisible(false);
	}
	
	public static boolean connect(String connection, int port){
		client = new Client(connection, port);
		if(client != null && client.isConnected){
			client.start();
			updateSalas();
		}
		return client.isConnected;
	}
	
	public static void setListData(){
		mainWindow.setListData(ClientInfo.getArrayGroups());
	}
	
	public static void updateSalas(){
		Message m = new Message();
		m.setStatus(Message.MessageType.REQUEST_GROUPS);
		client.send(m);
	}
	
	public static void sendMessage(Message m){
		client.send(m);
	}
	
	public static void openMessageWindow(String groupName){
		window.getFrame().setVisible(true);
		window.getFrame().setTitle("Divirta-se ( \u0361\u00B0 \u035C\u0296 \u0361\u00B0) - Sala: "  + groupName);
	}
	
	public static void enableMainWindow(){
		mainWindow.getFrame().setEnabled(true);
	}
	
	public static void addMessage(Message m){
		window.addMessage(m);
	}
	
	public static void showErrorMessage(String message){
		window.addErrorMessage(message);
	}
	
	public static void closeMessageWindow(){
		window.getFrame().setVisible(false);
		window.clearMessages();
	}
	
	public static void resetUI(){
		mainWindow.resetUI();
	}
	
	
}
