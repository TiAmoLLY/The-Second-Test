package com.lly.chatserver.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.lly.chat.model.User;





/**
 * 设计一个数据库访问类，这个类里面提供了所有整个项目中对数据库操作对方法
 * 
 *
 */
public class DatabaseUtil {

	/**
	 * 登陆
	 */
	public static  User login(String username,String password){
		//用javaio的方法将所有存储数据库信息的文件都反序列化到内存中做比较，判断这个账户名和密码的账号收否存在
		try {

			File  dataDir=new File("datas");
			File[] childs=dataDir.listFiles();
			for(int n=0;n<childs.length;n++) {
				ObjectInputStream  in=new ObjectInputStream(new FileInputStream(childs[n]));
				User user=(User)in.readObject();
				if(username.equals(user.getUsername())&&password.equals(user.getPassword())) {
					return user;
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 注册方法
	 */
	public static boolean register(User user) {
		try {
			File  f=new File("datas/"+user.getUsername()+".data");
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(user);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 读取所有注册的用户名
	 */
	public static   List<String> listAllUserNames(){
		List<String> allNames=new ArrayList<>();
		File  dataDir=new File("datas");
		File[] childs=dataDir.listFiles();
		for(int n=0;n<childs.length;n++) {
			allNames.add(childs[n].getName().substring(0, childs[n].getName().length()-5));
		}
		return allNames;
	}
	public static void main(String[] args) {
		  List<String>  all=listAllUserNames();
		  for(String a:all) {
			  System.out.println(a);
		  }
	}

}
