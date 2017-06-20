package model;

import java.io.Serializable;

public class Group implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int groupID;
	private String name;
	
	@Override
	public String toString() {
		return "ID: " + groupID + " Nome do grupo: " + name;
	}
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
