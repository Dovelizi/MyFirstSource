package com.jframe.examsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.jframe.examsystem.service.UserService;

public class ImplListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		//��ȡ�û�������˺ź�����
		//�ӵ�½�����ϵ�����ڻ�ȡ  �ı���  �����
		//���õ�½����
		UserService service = new UserService();
		//service.login(account, password);
		//�ж����յĽ��
		
		JOptionPane.showMessageDialog(null, "hello");
	}

}
