package SwingJieMian;

import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.lly.chat.model.Message;
import com.lly.chat.model.User;


public class RegisterFrame extends JFrame {

	private ObjectOutputStream  out;
	private ObjectInputStream  in;
	private LoginFrame  loginFrame;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	}

	/**
	 * Create the frame.
	 */
	
	public RegisterFrame(ObjectOutputStream  out,ObjectInputStream  in,LoginFrame  loginFrame) {
		this.loginFrame=loginFrame;
		this.in=in;
		this.out=out;
		
		//设置界面大小以及其他组件的相关设置
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450,100, 500, 600);
		setTitle("么么哒的熊孩子——注册");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("熊孩子账号：");
		lblUsername.setBounds(25, 25, 100, 25);
		contentPane.add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(115, 25,160, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("熊孩子密码：");
		lblPassword.setBounds(25,65, 100, 25);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(115, 65,160, 25);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		JLabel lblNickname = new JLabel("熊孩子昵称：");
		lblNickname.setBounds(25, 110, 100, 25);
		contentPane.add(lblNickname);
		
		textField_1 = new JTextField();
		textField_1.setBounds(115,110,160,25);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSex = new JLabel("性别：");
		lblSex.setBounds(25, 152, 60, 25);
		contentPane.add(lblSex);
		
		JRadioButton rdbtnMale = new JRadioButton("男");
		rdbtnMale.setContentAreaFilled(false);
		rdbtnMale.setBounds(65, 155, 60, 20);
		contentPane.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("女");
		rdbtnFemale.setContentAreaFilled(false);
		rdbtnFemale.setBounds(125, 155, 60, 20);
		contentPane.add(rdbtnFemale);
		ButtonGroup  sex=new ButtonGroup();
		
		sex.add(rdbtnMale);
		sex.add(rdbtnFemale);
		
		JLabel lblAge = new JLabel("熊孩子年龄：");
		lblAge.setBounds(25,195, 100, 25);
		contentPane.add(lblAge);
		
		textField_2 = new JTextField();
		textField_2.setBounds(115,195,80, 25);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		//注册功能的实现之添加监听事件
		JButton btnRegister = new JButton("注册");
		btnRegister.setContentAreaFilled(false);
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//1.	先获取用户在注册窗口上填写的信息
				String username=textField.getText();
				String password=passwordField.getText();
				String nickname=textField_1.getText();
				String sex=rdbtnMale.isSelected()?"男":"女";
				String age=textField_2.getText();
				//2.数据传输之前先封装成一个user对象
				User  user=new User(username,password,nickname,sex,Integer.parseInt(age));
				
				//3.通过网络将要注册的用户信息发送到服务器让服务器存下来
				Message  registerMessage=new Message();
				registerMessage.setFrom(user);
				registerMessage.setType("register");
				
				
				try {
					out.writeObject(registerMessage);
					out.flush();
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				//4.接收服务器给我回发的注册结果消息
				try {
					Message registerResult=(Message)in.readObject();
					if(registerResult.getContent().equals("success")) {
						int yourChoide=JOptionPane.showConfirmDialog(RegisterFrame.this, "注册成功，是否立即登陆", "注册结果", JOptionPane.INFORMATION_MESSAGE);
						System.out.println(yourChoide);
						if(yourChoide==0) {
							loginFrame.setVisible(true);
							RegisterFrame.this.dispose();
						}
					}else {
						JOptionPane.showMessageDialog(RegisterFrame.this, "温馨提斯：注册失败！", "注册结果", JOptionPane.ERROR_MESSAGE);
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRegister.setBounds(100, 233, 117, 29);
		contentPane.add(btnRegister);
		
		JButton btnLogin = new JButton("登录");
		btnLogin.setContentAreaFilled(false);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginFrame.setVisible(true);
				RegisterFrame.this.dispose();
			}
		});
		btnLogin.setBounds(238, 233, 117, 29);
		contentPane.add(btnLogin);
		
		//添加的背景图片
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 500, 300);
		lblNewLabel.setIcon(new ImageIcon("E:\\2.jpg"));
		contentPane.add(lblNewLabel);
		
		JLabel jpgButton=new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage("E:\\1.jpg").getScaledInstance(500,300, Image.SCALE_DEFAULT)));
		jpgButton.setBounds(0, 275,500, 300);
		jpgButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(jpgButton);
		contentPane.paintComponents(getGraphics());
	    this.paintAll(this.getGraphics());
	}

}
