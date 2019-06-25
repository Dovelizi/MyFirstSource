package com.jframe.examsystem.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

import com.jframe.examsystem.service.UserService;
import com.jframe.examsystem.util.BaseFrame;
import com.jframe.examsystem.util.MySpring;

public class Loginview extends BaseFrame {
	
	public Loginview() {
	
		this.init();
	}

	public Loginview(String title) {
		super(title);
		this.init();
	}

	// 创建面板
	private JPanel mainPanel = new JPanel();
	// 创建tile显示标题
	private JLabel titleLabel = new JLabel("在 线 考 试 系 统 ");
	private JLabel accountLabel = new JLabel("账 户：");
	private JLabel passwordLabel = new JLabel("密 码：");
	// 创建文本框
	private JTextField accountField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	// 创建按钮
	private JButton loginButton = new JButton("登 陆");
	private JButton exitButton = new JButton("退 出");

	// 设置每个主键的位置大小 布局
	protected void setFontAndOn() {
		// 设置组件的位置---布局管理
		// 边界式Border Layout（JFrame） 流式FlowLayout(JPanel)
		// 网格式Grid Layout 自定义（null）
		mainPanel.setLayout(null);
		// mainPanel.setBackground(Color.white);

		// 设置每一个组件的位置
		titleLabel.setBounds(120, 40, 340, 35);

		// 设置字体大小
		titleLabel.setFont(new Font("黑体", Font.BOLD, 34));
		// 设置用户名label位置和字体大小
		accountLabel.setBounds(75, 125, 90, 30);
		accountLabel.setFont(new Font("黑体", Font.BOLD, 24));
		// 设置密码label位置和字体大小
		passwordLabel.setBounds(75, 180, 90, 30);
		passwordLabel.setFont(new Font("黑体", Font.BOLD, 24));
		// 设置账户Field位置和大小
		accountField.setBounds(175, 125, 260, 30);
		accountField.setFont(new FontUIResource("黑体", Font.BOLD, 24));
		// 设置账户Field位置和大小
		passwordField.setBounds(175, 180, 260, 30);
		passwordField.setFont(new FontUIResource("黑体", Font.BOLD, 24));
		// 设置登陆按钮的属性
		loginButton.setBounds(154, 232, 100, 30);
		loginButton.setFont(new Font("黑体", Font.BOLD, 22));
		// 设置登陆按钮的属性
		exitButton.setBounds(304, 232, 100, 30);
		exitButton.setFont(new Font("黑体", Font.BOLD, 22));
	}

	// 将所有的 组件添加到窗体上
	protected void addElement() {
		// 将所有的组件添加至窗体上
		mainPanel.add(titleLabel);
		mainPanel.add(accountLabel);
		mainPanel.add(passwordLabel);
		mainPanel.add(accountField);
		mainPanel.add(passwordField);
		mainPanel.add(loginButton);
		mainPanel.add(exitButton);
		// this.add(mainPanel);
	}

	// 绑定事件监听
	protected void addListener() {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String account = accountField.getText();
				String password = new String(passwordField.getPassword());
				UserService service = MySpring.getBean("com.jframe.examsystem.service.UserService");
				String result = service.login(account, password);
				// 判断是否登陆成功
				if (result.equals("登陆成功")) {
					Loginview.this.setVisible(false);		//隐藏登陆窗口
					JOptionPane.showMessageDialog(Loginview.this, result);
					new ExamFrame(account + "考试界面");
				} else {
					JOptionPane.showMessageDialog(Loginview.this, result);
					// 登陆失败将文本空清空
					accountField.setText("");
					passwordField.setText("");
				}
			}
		};
		loginButton.addActionListener(listener);
		
		//退出键
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Loginview.this, "欢迎再次使用");
				System.exit(0);
			}
		});
	}

	protected void setFrameSelf() {
		// 设置窗体起始位置和大小
		this.setBounds(400, 200, 560, 340);
		// 设置点击关闭程序
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗体大小不可拖拽
		this.setResizable(false);
		// 设置显示状态
		this.setVisible(true);
		this.add(mainPanel);

	}

}
