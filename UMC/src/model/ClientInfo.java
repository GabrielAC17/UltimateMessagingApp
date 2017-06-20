package model;

import java.util.ArrayList;

public class ClientInfo {
	private static ArrayList<Group> groups = new ArrayList<Group>();
	private static String userName;
	private static Group currentGroup;

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		ClientInfo.userName = userName;
	}
	
	public static Group getCurrentGroup() {
		return currentGroup;
	}

	public static void setCurrentGroup(Group currentGroup) {
		ClientInfo.currentGroup = currentGroup;
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

	public static ArrayList<Group> getGroups() {
		return groups;
	}

	public static void setGroups(ArrayList<Group> groups) {
		ClientInfo.groups = groups;
	}
}
