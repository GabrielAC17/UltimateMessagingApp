package model;

import java.util.ArrayList;

import controller.ClientConnection;

public class ServerInfo {
	private static ArrayList<ClientConnection> clientes = new ArrayList<ClientConnection>();
	private static ArrayList<Group> groups = new ArrayList<Group>();
	private static int GroupID = 1;
	
	public static int getGroupID() {
		return GroupID;
	}

	public static void setGroupID() {
		GroupID++;
	}

	public static ArrayList<Group> getGroups() {
		return groups;
	}

	public static void setGroups(ArrayList<Group> g) {
		groups = g;
	}
	
	public static ArrayList<ClientConnection> getClientes() {
		return clientes;
	}

	public static void setClientes(ArrayList<ClientConnection> c) {
		clientes = c;
	}
	
	public static Group findGroup(String name){
		for(Group g : groups){
			if (g.getName().equals(name)){
				return g;
			}
		}
		return null;
	}
	
	public static Group findGroup(int id){
		for(Group g : groups){
			if (g.getGroupID() == id){
				return g;
			}
		}
		return null;
	}
	
	public static Group[] getArrayGroups(){
		Group[] aGroups = new Group[groups.size()];
		aGroups = groups.toArray(aGroups);
		return aGroups;
	}
}
