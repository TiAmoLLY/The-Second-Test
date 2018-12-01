package SwingJieMian;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.lly.chat.model.Message;
import com.lly.chat.model.User;

import SwingJieMian.ZhuYeMian;

public class LoginFrame extends JFrame {

	private Socket  client;
	private ObjectOutputStream  out;
	private ObjectInputStream  in;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel name,password;
	private JCheckBox rememberPassword,ziDongDengLu;
	private JLabel lblNewLabel;//背景图片

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public LoginFrame() {
		
		//设置整个界面的大小以及标题
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 250, 450, 300);
		setTitle("么么哒的熊孩子——登陆");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//设置其他组件
		name=new JLabel("熊孩子账号：");
		name.setBounds(85,50,80,20);
		contentPane.add(name);
		
		
		
		textField = new JTextField();
		textField.setBounds(175,50,160,20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		password=new JLabel("熊孩子密码：");
		password.setBounds(85,70,80,20);
		contentPane.add(password);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(175, 70, 160, 20);
		contentPane.add(passwordField);
		
		rememberPassword=new JCheckBox("记住密码");
		rememberPassword.setBounds(80, 150, 100, 20);
		rememberPassword.setContentAreaFilled(false);//使透明化
		contentPane.add(rememberPassword);
		
		ziDongDengLu=new JCheckBox("自动登录");
		ziDongDengLu.setBounds(200, 150, 100, 20);
		ziDongDengLu.setContentAreaFilled(false);
		contentPane.add(ziDongDengLu);
		
		JButton button_2 = new JButton("找回密码");
		button_2.setBounds(260, 110, 100, 20);
		button_2.setContentAreaFilled(false);
		contentPane.add(button_2);
		
		//在登录按钮上设置监听事件
		JButton button = new JButton("登陆");
		button.setContentAreaFilled(false);
		this.setVisible(true);
		this.paintAll(this.getGraphics());
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//1.取街面上输入的用户名和密码
				String username=textField.getText();
				String password=passwordField.getText();
				
				//2.将登陆内容封装成一个标准的消息对象
				User  user=new User(username,password);
				
				Message  loginMessage=new Message();
				loginMessage.setFrom(user);
				loginMessage.setType("login");
				
				//3.使用底层的输出流将消息发送给服务器
				try {
					out.writeObject(loginMessage);
					out.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				//4.接受服务器给我回发的登陆结果消息
				try {
					Message loginResult=(Message)in.readObject();
					if(loginResult.getFrom()!=null) {
						ZhuYeMian  m=new ZhuYeMian(loginResult.getFrom(),loginResult.getAllUser(),out,in);
						m.setVisible(true);
						LoginFrame.this.dispose();
					}else {
						JOptionPane.showMessageDialog(LoginFrame.this, "呀！熊孩子出错啦！！！请检查用户名和密码！", "登陆结果", JOptionPane.ERROR_MESSAGE);
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(80, 110, 60, 20);
		contentPane.add(button);
		
		//在注册按钮上设置监听事件
		JButton button_1 = new JButton("注册");
		button_1.setContentAreaFilled(false);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterFrame  r=new RegisterFrame(out,in,LoginFrame.this);
				r.setVisible(true);
				LoginFrame.this.setVisible(false);
			}
		});
		button_1.setBounds(170, 110, 60, 20);
		contentPane.add(button_1);
		
		//添加背景图片
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 434, 261);
		lblNewLabel.setIcon(new ImageIcon("E:\\3.jpg"));
		contentPane.add(lblNewLabel);
		
		//一启动登录页面就连接服务器
		try {
			client=new Socket("localhost", 1010);
			System.out.println("连接上了");
			//注意这里的i/o流顺序需要与服务器的i/o流顺序设置相反，不然同时读或者同时取会导致整个程序阻塞
			out=new ObjectOutputStream(client.getOutputStream());
			in = new ObjectInputStream(client.getInputStream());
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
