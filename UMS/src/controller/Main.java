package controller;

import view.ViewServer;

public class Main {	
	public static void main(String[] args) {
		ViewServer window = new ViewServer();
		window.getFrame().setVisible(true);
	}
	
	public static void iniciarServidor(int port){
		Server server = new Server(port);
		server.start();
	}
}