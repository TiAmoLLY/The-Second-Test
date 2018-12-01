package SwingJieMian;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.MutableTreeNode;

import com.lly.chat.model.Message;
import com.lly.chat.model.User;


public class ZhuYeMian extends JFrame{
	private JPanel contentPane;
	private User loginedUser;
	private List<String>  allUsers;
	private ObjectOutputStream out;
	private ObjectInputStream  in;
	private JLabel lblNewLabel;
	
	//声明一个map类型集合，存储所有打开过的好友窗口
	private Map<String, ChatFrame>  allChatWindows;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	}

	/**
	 * Create the frame.
	 */
	public ZhuYeMian(User loginedUser, List<String>  allUsers,ObjectOutputStream out,ObjectInputStream in) {
		this.out=out;
		this.in=in;
		allChatWindows=new HashMap<>();
		this.allUsers=allUsers;
		this.loginedUser=loginedUser;
		
		//完成界面大小的设置以及其他相关组件的基本设置
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 193, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		
		JLabel lblNewLabel = new JLabel(loginedUser.getNickname());
		lblNewLabel.setBounds(16, 18, 134, 21);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel(loginedUser.getSex());
		label.setBounds(6, 51, 61, 16);
		contentPane.add(label);
		
		//添加的背景图片
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 190, 78);
		ImageIcon background=new ImageIcon("E:\\5.jpg");
		lblNewLabel.setIcon(background);
		contentPane.add(lblNewLabel);
		contentPane.paintComponents(getGraphics());
		this.paintAll(this.getGraphics());
		
		
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("熊孩子们");
		JTree tree = new JTree(root);
//		tree.setOpaque(false);

		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2&&e.getButton()==1) {
					if(tree.getSelectionPath().getPathCount()==2)
					{
						
						String username=tree.getSelectionPath().getLastPathComponent().toString();
						
						if(allChatWindows.containsKey(username)) {
							allChatWindows.get(username).setVisible(true);//之前打开过这个好友的聊天窗口，此时只是从集合里把它拿出来然后调用setvisiable方法让这个窗口显示
						}else {
							ChatFrame  c=new ChatFrame(loginedUser,username,out,in);
							c.setVisible(true);
							allChatWindows.put(username, c);//如果是第一次打开，打开完之后将这个好友和窗口对应关系存入到集合里
						}
					}
				}
			}
		});
		JScrollPane  scroll=new JScrollPane(tree);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		//为了能让所有的用户显示在好友列表中的tree中，我们需要遍历这个好友的集合，将集合中每个用户名字加入到jtree上
		for(String name:allUsers) {
			MutableTreeNode  oneUser=new DefaultMutableTreeNode(name);
			root.add(oneUser);
		}
		scroll.setBounds(16, 79, 162, 181);
		contentPane.add(scroll);
		
		//在主界面的构造器最后调用自己额外封装开启接受消息线程的方法（相当于是界面一打开，底层就开了一个线程不停的接受服务器给我推送过来的消息）
		startMessageReciverThread();
		
	}
	//创建线程以完成实时聊天功能，接受消息和发送消息
	public void startMessageReciverThread(){
		class  MessageReciverThread extends Thread{
			@Override
			public void run() {
				while(true) {
					try {
						Message  message=(Message)in.readObject();
						if(allChatWindows.containsKey(message.getFrom().getUsername())) {
							allChatWindows.get(message.getFrom().getUsername()).setVisible(true);//之前打开过这个好友的聊天窗口，此时只是从集合里把它拿出来然后调用setvisiable方法让这个窗口显示
							allChatWindows.get(message.getFrom().getUsername()).getTextArea().append(message.getFrom().getUsername()+"\t"+message.getDate()+"\r\n"+message.getContent()+"\r\n\r\n");
						}else {
							ChatFrame  c=new ChatFrame(loginedUser,message.getFrom().getUsername(),out,in);
							c.setVisible(true);
							c.getTextArea().append(message.getFrom().getUsername()+"\t"+message.getDate()+"\r\n"+message.getContent()+"\r\n\r\n");
							allChatWindows.put(message.getFrom().getUsername(), c);//如果是第一次打开，打开完之后将这个好友和窗口对应关系存入到集合里
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		new MessageReciverThread().start();
		
	}
	

	

}
