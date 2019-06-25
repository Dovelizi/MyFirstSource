package com.jframe;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JFrameDemo {
	public static void main(String[] args) {
		//创建一个窗体对象
		JFrame frame = new JFrame("我的第一个窗体");
		//创建一个按钮对象
		JButton button = new JButton("我是按钮");
		//button.setBounds(0, 400, 80, 40);
		//设置窗体的属性
		frame.setVisible(true);
		//创建一个面板
		JPanel panel = new JPanel();
		//设置点击关闭
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//创建文本框
		JTextField text = new JTextField(10);
		//设置窗体出现的位置和宽高
		frame.setBounds(500,300,555,555);
		
		//添加一些组件
		panel.add(text);
		panel.add(button);
		
		
		frame.add(panel);
		
		

	}

}
