package com.lly.chat.model;

import java.io.Serializable;
import java.util.List;

/**
 * 封装一中消息类型，为了方便后期软件在通许中所有要传递的信息都封装成这种标准都对象，方便解析
 *
 */
public class Message implements Serializable {
	private  User  from;
	private  User to;
	private  String content;
	private  String type;
	private  String date;
	private  List<String> allUser;
	public Message() {
		super();
	}
	public Message(User from, User to, String content, String type, String date) {
		super();
		this.from = from;
		this.to = to;
		this.content = content;
		this.type = type;
		this.date = date;
	}
	@Override
	public String toString() {
		return "Message [from=" + from + ", to=" + to + ", content=" + content + ", type=" + type + ", date=" + date
				+ ", allUser=" + allUser + "]";
	}
	public User getFrom() {
		return from;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	public User getTo() {
		return to;
	}
	public void setTo(User to) {
		this.to = to;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public List<String> getAllUser() {
		return allUser;
	}
	public void setAllUser(List<String> allUser) {
		this.allUser = allUser;
	}

}



