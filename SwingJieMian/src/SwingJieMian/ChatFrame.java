package SwingJieMian;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.lly.chat.model.Message;
import com.lly.chat.model.User;


public class ChatFrame extends JFrame{
	private JPanel contentPane;

	private String friendName;
	private ObjectOutputStream out;
	private ObjectInputStream  in;
	private User myInfo;
	private JTextArea textArea ;
	private JLabel lblNewLabel;
	
	public JTextArea getTextArea() {
		return textArea;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	}

	/**
	 * Create the frame.
	 */
	public ChatFrame(User myInfo,String friendName,ObjectOutputStream  out,ObjectInputStream in) {
		this.myInfo=myInfo;
		this.out=out;
		this.in=in;
		this.friendName=friendName;
		
		//设置界面的大小以及完成其他组件的相关设置
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100,450, 350);
		this.setTitle("与"+friendName+"熊孩子欢乐聊天中！！！");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 438, 124);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 137, 438, 94);
		contentPane.add(scrollPane_1);
		
		JTextArea textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		//完成聊天内容的发送之发送按钮的监听事件
		JButton btnSend = new JButton("发送");
		btnSend.setContentAreaFilled(false);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//1.先获取消息编辑框里的输入内容
					String willSendText=textArea_1.getText();
				//2.把编辑框里消息晴空
					textArea_1.setText("");
				//3.把消息显示到上面的消息对话框中	
					
					textArea.append(myInfo.getNickname()+"\t"+new Date().toLocaleString()+"\r\n"+willSendText+"\r\n\r\n");
				//4.把要发出去的聊天内容封装成的一个标准的Message对象
					Message  willSendMessage=new Message();
					willSendMessage.setFrom(myInfo);
					willSendMessage.setTo(new User(friendName, null));
					willSendMessage.setContent(willSendText);
					willSendMessage.setType("textMessage");
				//5.使用输出流将封装好的一个消息对象发送出去
					try {
						out.writeObject(willSendMessage);
						out.flush();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
			}
		});
		btnSend.setBounds(165, 243, 117, 29);
		contentPane.add(btnSend);
		
		JButton btnClose = new JButton("取消");
		btnClose.setContentAreaFilled(false);
		btnClose.setBounds(293, 243, 117, 29);
		contentPane.add(btnClose);
		contentPane.paintComponents(getGraphics());
		this.paintAll(this.getGraphics());
		
		//添加的背景图片
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 450,350);
		lblNewLabel.setIcon(new ImageIcon("E:\\6.jpg"));
		contentPane.add(lblNewLabel);
	}

}
