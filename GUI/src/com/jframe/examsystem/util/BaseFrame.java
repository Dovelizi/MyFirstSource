package com.jframe.examsystem.util;

import javax.swing.JFrame;

public abstract class BaseFrame extends JFrame {

	public BaseFrame() {
		
	}
	public BaseFrame(String title) {
		super(title);
	}
	
	//ģ��ģʽ---->���һ���̶���ģ���ṩ����ȥ�̳�
	//���������ִ������
	protected void init() {
		this.setFontAndOn();
		this.addElement();
		this.addListener();
		this.setFrameSelf();
	}
	//���� ���� ��ɫ ���� ����
	protected abstract void setFontAndOn();
	//��������ӵ�������
	protected abstract void addElement();
	//��Ӽ����¼�
	protected abstract void addListener();
	//���ô����λ�ô�С������
	protected abstract void setFrameSelf();
	
}
