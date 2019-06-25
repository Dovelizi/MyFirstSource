package com.jframe.examsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.jframe.examsystem.service.UserService;

public class ImplListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		//获取用户输入的账号和密码
		//从登陆窗口上的组件内获取  文本框  密码框
		//调用登陆方法
		UserService service = new UserService();
		//service.login(account, password);
		//判定最终的结果
		
		JOptionPane.showMessageDialog(null, "hello");
	}

}
