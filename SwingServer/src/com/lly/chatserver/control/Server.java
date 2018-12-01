package com.lly.chatserver.control;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lly.chat.model.Message;
import com.lly.chat.model.User;



public class Server {

	private ServerSocket  server;
	private Map<String,ObjectOutputStream> allClients;
	public Server() {
		try {
			server=new ServerSocket(1010);
			allClients=new HashMap<>();
			while(true) {
				Socket client=server.accept();
				
				MessageReciverThread  thread=new MessageReciverThread(client);
				thread.start();
				System.out.println(client.getInetAddress().getHostAddress());
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Server();
	}
	class MessageReciverThread extends Thread{
		private   Socket c;
		private  ObjectInputStream  in;
		private  ObjectOutputStream  out;

		public MessageReciverThread(Socket c) {
			super();
			this.c = c;
			try {
				in=new ObjectInputStream(c.getInputStream());
				out=new ObjectOutputStream(c.getOutputStream());
			} catch (Exception e) {
			}
		}
		@Override
		public void run() {
			try {
			
				while(true) {
					Message message=(Message)in.readObject();
					if(message.getType().equals("register"))
					{
						System.out.println("register message"+message);
						boolean result=DatabaseUtil.register(message.getFrom());
						
						//服务器再封装一个注册结果都Message对象发送给客户端
						Message   registerResult=new Message();
						registerResult.setContent(result?"success":"fail");
						out.writeObject(registerResult);
						out.flush();
					}else if(message.getType().equals("login"))
					{
						//1.根据传过来的登陆信息查询数据库返回查询的结果
						System.out.println("login message"+message);
						User user=DatabaseUtil.login(message.getFrom().getUsername(), message.getFrom().getPassword());
						//将登陆的人的信息存到Map里面去
						allClients.put(message.getFrom().getUsername(), out);
						
						//2.将查询出来的这个用户对象直接封装成一个消息回给客户端
						//除了要将登陆的用户资料查询返回给客户端之外还要讲所有注册的用户列表这个集合数据返回给客户端
						Message   loginResult=new Message();
						loginResult.setFrom(user);
						
						List<String> all=DatabaseUtil.listAllUserNames();
						loginResult.setAllUser(all);;
						out.writeObject(loginResult);
						out.flush();
						
					}
					else if(message.getType().equals("textMessage")) {
						System.out.println("服务器接收到一个客户发过来的聊天消息："+message);
						String reciveUserName=message.getTo().getUsername();
						
						if(allClients.containsKey(reciveUserName)) {
							message.setDate(new Date().toLocaleString());
							allClients.get(reciveUserName).writeObject(message);
							allClients.get(reciveUserName).flush();
							System.out.println("服務器找到了这个登陆的用户，将消息转发出去了");
						}else {
							System.out.println("该用户没有在线，不转发消息");
						}
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
