package com.jframe.examsystem.util;

import javax.swing.JFrame;

public abstract class BaseFrame extends JFrame {

	public BaseFrame() {
		
	}
	public BaseFrame(String title) {
		super(title);
	}
	
	//模板模式---->设计一个固定的模板提供子类去继承
	//设置组件的执行流程
	protected void init() {
		this.setFontAndOn();
		this.addElement();
		this.addListener();
		this.setFrameSelf();
	}
	//设置 字体 颜色 背景 布局
	protected abstract void setFontAndOn();
	//将属性添加到窗口里
	protected abstract void addElement();
	//添加监听事件
	protected abstract void addListener();
	//设置窗体的位置大小等属性
	protected abstract void setFrameSelf();
	
}
