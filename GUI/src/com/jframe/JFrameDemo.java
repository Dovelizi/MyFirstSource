package com.jframe;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JFrameDemo {
	public static void main(String[] args) {
		//����һ���������
		JFrame frame = new JFrame("�ҵĵ�һ������");
		//����һ����ť����
		JButton button = new JButton("���ǰ�ť");
		//button.setBounds(0, 400, 80, 40);
		//���ô��������
		frame.setVisible(true);
		//����һ�����
		JPanel panel = new JPanel();
		//���õ���ر�
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//�����ı���
		JTextField text = new JTextField(10);
		//���ô�����ֵ�λ�úͿ��
		frame.setBounds(500,300,555,555);
		
		//���һЩ���
		panel.add(text);
		panel.add(button);
		
		
		frame.add(panel);
		
		

	}

}
