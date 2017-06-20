package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static enum MessageType{
		EMPTY, 
		TEXT_MESSAGE, 
		GROUP_JOIN, 
		GET_MESSAGES_FROM_GROUP,
		GET_GROUPS, 
		RECEIVE_MESSAGE, 
		REQUEST_GROUPS
	}
	
	private String message;
	private int id;
	private Group currentGroup;
	private ArrayList<Group> allGroups = new ArrayList<Group>();
	private MessageType status;
	private String userName;
	private Date time;
	
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public MessageType getStatus() {
		return status;
	}
	public void setStatus(MessageType status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Group getCurrentGroup() {
		return currentGroup;
	}
	public void setCurrentGroup(Group currentGroup) {
		this.currentGroup = currentGroup;
	}
	public ArrayList<Group> getAllGroups() {
		return allGroups;
	}
	public void setAllGroups(ArrayList<Group> allGroups) {
		this.allGroups = allGroups;
	}
}
