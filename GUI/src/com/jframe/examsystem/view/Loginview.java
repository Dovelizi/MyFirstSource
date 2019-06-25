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

	// �������
	private JPanel mainPanel = new JPanel();
	// ����tile��ʾ����
	private JLabel titleLabel = new JLabel("�� �� �� �� ϵ ͳ ");
	private JLabel accountLabel = new JLabel("�� ����");
	private JLabel passwordLabel = new JLabel("�� �룺");
	// �����ı���
	private JTextField accountField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	// ������ť
	private JButton loginButton = new JButton("�� ½");
	private JButton exitButton = new JButton("�� ��");

	// ����ÿ��������λ�ô�С ����
	protected void setFontAndOn() {
		// ���������λ��---���ֹ���
		// �߽�ʽBorder Layout��JFrame�� ��ʽFlowLayout(JPanel)
		// ����ʽGrid Layout �Զ��壨null��
		mainPanel.setLayout(null);
		// mainPanel.setBackground(Color.white);

		// ����ÿһ�������λ��
		titleLabel.setBounds(120, 40, 340, 35);

		// ���������С
		titleLabel.setFont(new Font("����", Font.BOLD, 34));
		// �����û���labelλ�ú������С
		accountLabel.setBounds(75, 125, 90, 30);
		accountLabel.setFont(new Font("����", Font.BOLD, 24));
		// ��������labelλ�ú������С
		passwordLabel.setBounds(75, 180, 90, 30);
		passwordLabel.setFont(new Font("����", Font.BOLD, 24));
		// �����˻�Fieldλ�úʹ�С
		accountField.setBounds(175, 125, 260, 30);
		accountField.setFont(new FontUIResource("����", Font.BOLD, 24));
		// �����˻�Fieldλ�úʹ�С
		passwordField.setBounds(175, 180, 260, 30);
		passwordField.setFont(new FontUIResource("����", Font.BOLD, 24));
		// ���õ�½��ť������
		loginButton.setBounds(154, 232, 100, 30);
		loginButton.setFont(new Font("����", Font.BOLD, 22));
		// ���õ�½��ť������
		exitButton.setBounds(304, 232, 100, 30);
		exitButton.setFont(new Font("����", Font.BOLD, 22));
	}

	// �����е� �����ӵ�������
	protected void addElement() {
		// �����е���������������
		mainPanel.add(titleLabel);
		mainPanel.add(accountLabel);
		mainPanel.add(passwordLabel);
		mainPanel.add(accountField);
		mainPanel.add(passwordField);
		mainPanel.add(loginButton);
		mainPanel.add(exitButton);
		// this.add(mainPanel);
	}

	// ���¼�����
	protected void addListener() {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String account = accountField.getText();
				String password = new String(passwordField.getPassword());
				UserService service = MySpring.getBean("com.jframe.examsystem.service.UserService");
				String result = service.login(account, password);
				// �ж��Ƿ��½�ɹ�
				if (result.equals("��½�ɹ�")) {
					Loginview.this.setVisible(false);		//���ص�½����
					JOptionPane.showMessageDialog(Loginview.this, result);
					new ExamFrame(account + "���Խ���");
				} else {
					JOptionPane.showMessageDialog(Loginview.this, result);
					// ��½ʧ�ܽ��ı������
					accountField.setText("");
					passwordField.setText("");
				}
			}
		};
		loginButton.addActionListener(listener);
		
		//�˳���
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Loginview.this, "��ӭ�ٴ�ʹ��");
				System.exit(0);
			}
		});
	}

	protected void setFrameSelf() {
		// ���ô�����ʼλ�úʹ�С
		this.setBounds(400, 200, 560, 340);
		// ���õ���رճ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ���ô����С������ק
		this.setResizable(false);
		// ������ʾ״̬
		this.setVisible(true);
		this.add(mainPanel);

	}

}
