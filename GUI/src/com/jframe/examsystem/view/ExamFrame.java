package com.jframe.examsystem.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import com.domain.Question;
import com.jframe.examsystem.service.QuestionService;
import com.jframe.examsystem.util.BaseFrame;
import com.jframe.examsystem.util.MySpring;

public class ExamFrame extends BaseFrame  {

	//获取QuestionService对象
	private QuestionService service = MySpring.getBean("com.jframe.examsystem.service.QuestionService");
	//通过service调用getPaper方法获取一套随机生成的试卷
	private ArrayList<Question> paper = service.getPaper(8);
	//创建一个存储答案的容器
	private String[] answers = new String[paper.size()];
	//创建几个变量  分别控制右侧的数的变化
	private int nowNum = 0;									//记录当前题号
	private int totleCount = paper.size();					//记录题目总数
	private int answerCount = 0;							//记录已经答题数
	private int unanswerCount = totleCount - answerCount;	//未答题数
	//创建一个线程对象 控制时间变化
	private TimeControlThread timeControlThread = new TimeControlThread();
	//创建一个变量  用来记录时间(以分钟为单位)
	private int time = 10;
	
	//*************************以下为组件的绘制*******************************
	public ExamFrame() {
		this.init();
	}
	public ExamFrame(String title) {
		super(title);
		this.init();	
	}
	//绘画主页面
	private JPanel mainPanel = new JPanel();		//主答题区
	private JPanel messagePanel = new JPanel();		//答题信息显示区
	private JPanel buttonPanel = new JPanel();		//按扭去
	
	//
	private JTextArea examArea = new JTextArea();				//答题内容文本框
	private JScrollPane scrollPane = new JScrollPane(examArea);	//滚动条
	//
	private JLabel pictureLabel = new JLabel();					//图片展示区
	private JLabel currLabel = new JLabel("当前题号：");			//显示当前题号
	private JLabel totleCountLabel = new JLabel("题目总数：");		//显示答题总数
	private JLabel answerCountLabel = new JLabel("已答题数：");	//显示已经答题数
	private JLabel unanswerCountLabel = new JLabel("未答题数:");	//显示未答题数
	//以上要显示的具体数值
	private JTextField currField = new JTextField();
	private JTextField totleCountField = new JTextField();
	private JTextField answerCountField = new JTextField();
	private JTextField unanswerCountField = new JTextField();
	//显示时间栏
	private JLabel timeLabel = new JLabel("剩余答题时间：");
	private JLabel realTimeLabel = new JLabel("00:00:00");
	//按钮区
	private JButton aButton = new JButton("A");
	private JButton bButton = new JButton("B");
	private JButton cButton = new JButton("C");
	private JButton dButton = new JButton("D");
	private JButton prevButton = new JButton("上一题");
	private JButton nextButton = new JButton("下一题");
	private JButton summitButton = new JButton("提 交");
	
	@Override
	protected void setFontAndOn() {
		//设置自定义布局管理
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.LIGHT_GRAY);
		//设置messsage区域
		messagePanel.setLayout(null);
		messagePanel.setBounds(680, 10, 300, 550);
		messagePanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		//设置按钮区域
		buttonPanel.setLayout(null);
		buttonPanel.setBounds(16, 470, 650, 90);
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.green));
		//设置字体
		scrollPane.setBounds(16, 10, 650, 450);
		examArea.setFont(new FontUIResource("黑体", Font.BOLD, 24));
		//默认在用户一登陆就展示第一题
		
		examArea.setEnabled(false);									//文本框中的内容不能更改	
		//设置message区域的每一个组件的位置
		//第一组
		pictureLabel.setBounds(10, 10, 280, 230);
		pictureLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		pictureLabel.setIcon(null); 	//展示图片信息
		currLabel.setBounds(20, 270, 140, 30);
		currLabel.setFont(new Font("黑体", Font.PLAIN, 25));
		currField.setBounds(150, 270, 100, 30);
		currField.setFont(new Font("黑体", Font.BOLD, 20));
		currField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		currField.setEnabled(false);
		currField.setHorizontalAlignment(JTextField.CENTER);
		//第二组
		totleCountLabel.setBounds(20, 310, 140, 30);
		totleCountLabel.setFont(new Font("黑体", Font.PLAIN, 25));
		totleCountField.setBounds(150, 310, 100, 30);
		totleCountField.setFont(new Font("黑体", Font.BOLD, 20));
		totleCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		totleCountField.setEnabled(false);
		totleCountField.setHorizontalAlignment(JTextField.CENTER);
		//第三组
		answerCountLabel.setBounds(20, 350, 140, 30);
		answerCountLabel.setFont(new Font("黑体", Font.PLAIN, 25));
		answerCountField.setBounds(150, 350, 100, 30);
		answerCountField.setFont(new Font("黑体", Font.BOLD, 20));
		answerCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		answerCountField.setEnabled(false);
		answerCountField.setHorizontalAlignment(JTextField.CENTER);
		//第四组
		unanswerCountLabel.setBounds(20, 390, 140, 30);
		unanswerCountLabel.setFont(new Font("黑体", Font.PLAIN, 25));
		unanswerCountField.setBounds(150, 390, 100, 30);
		unanswerCountField.setFont(new Font("黑体", Font.BOLD, 20));
		unanswerCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		unanswerCountField.setEnabled(false);
		unanswerCountField.setHorizontalAlignment(JTextField.CENTER);
		//第五组
		timeLabel.setBounds(90, 470, 150, 30);
		timeLabel.setFont(new Font("黑体", Font.PLAIN, 20));
		timeLabel.setForeground(Color.green);
		realTimeLabel.setBounds(108, 500, 150, 30);
		realTimeLabel.setFont(new Font("黑体", Font.BOLD, 20));
		realTimeLabel.setForeground(Color.green);
		//按钮的布局
		aButton.setBounds(40, 10, 120, 30);
		bButton.setBounds(190, 10, 120, 30);
		cButton.setBounds(340, 10, 120, 30);
		dButton.setBounds(490, 10, 120, 30);	
		prevButton.setBounds(40, 50, 100, 30);
		nextButton.setBounds(510, 50, 100, 30);
		summitButton.setBounds(276, 50, 120, 30);
		summitButton.setForeground(Color.red);
		summitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));   //改变鼠标样式
	
		//++++++++++++++++++++++++++++++++++++++++++
		//展示第一题
		this.showQuestionAndPicture();
		//重新设置右侧的message中的组件-----变量值
		currField.setText(nowNum + 1 + "");
		totleCountField.setText(totleCount + "");
		answerCountField.setText(answerCount + "");
		unanswerCountField.setText(unanswerCount + "");
		
	}

	@Override
	protected void addElement() {
		messagePanel.add(pictureLabel);
		/**添加组件在画板中*/
		messagePanel.add(currLabel);
		messagePanel.add(currField);
		//组件二
		messagePanel.add(totleCountLabel);
		messagePanel.add(totleCountField);
		//组件三
		messagePanel.add(answerCountLabel);
		messagePanel.add(answerCountField);
		//组件四
		messagePanel.add(unanswerCountLabel);
		messagePanel.add(unanswerCountField);
		//组件五
		messagePanel.add(timeLabel);
		messagePanel.add(realTimeLabel);
		//添加按钮到按钮话板中
		buttonPanel.add(aButton);
		buttonPanel.add(bButton);
		buttonPanel.add(cButton);
		buttonPanel.add(dButton);
		buttonPanel.add(prevButton);
		buttonPanel.add(nextButton);
		buttonPanel.add(summitButton);
		//在主画板中添加三块小画板
		mainPanel.add(scrollPane);
		mainPanel.add(messagePanel);
		mainPanel.add(buttonPanel);
		//最后将主画板加入到JFrame控件中
		this.add(mainPanel);
		
	}

	@Override
	protected void addListener() {
		//创建一个监听器对象  用于提交按钮
		summitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//给用户提示是否执行改操作
				
				//倒计时的时间停止
				//timeControlThread.stop();
				timeControlThread.stopTimeThread(false);
				//所有按钮失效
				ExamFrame.this.setOptionButtonEnabled(false);
				ExamFrame.this.clearOptionButtonColor();
				prevButton.setEnabled(false);
				//打印最终成绩并
				float score = ExamFrame.this.checkPaper();
				//JOptionPane.showConfirmDialog(ExamFrame.this, "是否交卷？");
				int result = JOptionPane.showConfirmDialog(ExamFrame.this, "是否交卷？");
				if(result == 0) {
					JOptionPane.showMessageDialog(ExamFrame.this, "您的最终得分为：\n"+ExamFrame.this.checkPaper());
					examArea.setText("考试结束！\n 您的最终成绩为" + score);
				}else {
					timeControlThread.stopTimeThread(true);
				}
			}
		});
		
		
		//创建上一题的按钮的监听器
		prevButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//将所有按钮的可选择性改为true
				ExamFrame.this.setOptionButtonEnabled(true);
				//清空所有按钮的颜色
				ExamFrame.this.clearOptionButtonColor();
				//当前题号减少一个
				nowNum--;
				
				//如果当前题号为0 则禁用上一题按钮
				if(nowNum == 0) {
					prevButton.setEnabled(false);
				}
				//显示当前退回的题目的同时还愿以前的选项
				ExamFrame.this.restoreButton();
				ExamFrame.this.showQuestionAndPicture();
				//修改右侧的数值
				currField.setText(nowNum + 1 + "");
				answerCountField.setText(--answerCount + "");
				unanswerCountField.setText(++unanswerCount  + "");
			}
		});
		
		//创建下一的按钮的监听器
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//先清除所有按钮的颜色
				ExamFrame.this.clearOptionButtonColor();
				//当前题号加1 
				nowNum++;
				//判断当前的题号是否未最后一题
				if(nowNum == totleCount) {//已经加载完全部题
					//此时可以让所有按钮不能点击
					ExamFrame.this.setOptionButtonEnabled(false);
					prevButton.setEnabled(true);
					examArea.setText("全部题目已经回答完毕！\n若检查无误后请点击提交按钮进行试卷的提交");
				}else {
					//调用自己封装的显示题目的方法
					ExamFrame.this.showQuestionAndPicture();
					//修改右侧的显示信息
					currField.setText(nowNum + 1 + "");	
					//如果用户重复操作，则需要再重新获取已选答案
					ExamFrame.this.restoreButton();
				}
				//已经答过的题和未答的
				answerCountField.setText(++answerCount + "");
				unanswerCountField.setText(--unanswerCount  + "");
			}
		});
		
		
		//创建一个监听器  --- 用于四个选项的按钮
		ActionListener optionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			//清空之前的按钮的颜色
			ExamFrame.this.clearOptionButtonColor();
			//获取到点击的哪个按钮
			JButton button = (JButton)e.getSource();		
			//让颜色变化一下
			button.setBackground(Color.green);
			//将当前按钮的选项存储在answers数组中
			answers[nowNum] = button.getText();
			}
		};
		//分别绑定这四个按钮
		aButton.addActionListener(optionListener);
		bButton.addActionListener(optionListener);
		cButton.addActionListener(optionListener);
		dButton.addActionListener(optionListener);
		
		
	}

	@Override
	protected void setFrameSelf() {
		this.setBounds(260, 180, 1000, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		//在初始化图像界面的时候倒计时的线程准备就绪
		timeControlThread.start();
		
	}
	
	//*****************************展示题目的方法************************************
	
	//设计一个方法（内部类） 处理时间的变化
	@SuppressWarnings("unused")
	private class TimeControlThread extends Thread{
		private boolean falg = true;
		public void stopTimeThread(boolean falg) {
			this.falg = falg;
		}
		public void run() {
			
			int hour = time / 60;
			int minute = time % 60;
			int second = 0;
			//将时间转化为相应的时间格式并展示出来
			//realTimeLabel.setText(ExamFrame.this.transformTime(time));
			//不停的变化时间
			while(falg) {
				StringBuffer timeString = new StringBuffer();
				//处理小时
				if(hour >= 0 && hour <10) {
					timeString.append("0");
				}
				timeString.append(hour);
				timeString.append(":");
				//处理分钟
				if(minute >= 0 && minute <10) {
					timeString.append("0");
				}
				timeString.append(minute);
				timeString.append(":");
				//处理秒
				if(second >= 0 && second <10) {
					timeString.append("0");
				}
				timeString.append(second);
				//展示拼接以后的时间
				/**
				 * 如果时间的倒计时有误的话 可以再调用util包中的一个小线程
				 * SwingUtilities.invokeLater(new Runnable(){
				 * 	public void run(){
				 * 		
				 * 		realTimeLabel.setText(timeString.toString());
				 * });
				 */
				realTimeLabel.setText(timeString.toString());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//改变时间
				if(second > 0) {
					second--;
				}else {
					if(minute > 0) {
						minute--;
						second = 59;
					}else {
						if(hour > 0) {
							hour--;
							minute = 59;
							second = 59;
						}else {
							JOptionPane.showMessageDialog(ExamFrame.this, "考试时间到！请交卷");
							//将除提交的所有按钮都禁用
							ExamFrame.this.setOptionButtonEnabled(false);
							prevButton.setEnabled(false);
							//将时间颜色变红
							realTimeLabel.setForeground(Color.red);
						}
						break;
					}
				}
			}
		}
	}
	//^^^^^^^^^^^^^^^^^^^^^^^计算成绩^^^^^^^^^^^^^^^^^^^^^^^^
	private float checkPaper() {
		float score = 0;
		int size = paper.size();
		for (int i = 0; i < size; i++) {
			Question question = paper.get(i);
			String realAnswer = question.getAnswer();
			if(realAnswer.equals(answers[i])) {
				score += (100/size);
			}	
		}
		return score;
		
	}
	
//	public String transformTime(int time) {
//		int hour = time / 60;
//		int minute = time % 60;
//		int second = 0;
//		
//		StringBuffer timeString = new StringBuffer();
//		//处理小时
//		if(hour >= 0 && hour <10) {
//			timeString.append("0");
//		}
//		timeString.append(hour);
//		timeString.append(":");
//		//处理分钟
//		if(minute >= 0 && minute <10) {
//			timeString.append("0");
//		}
//		timeString.append(minute);
//		timeString.append(":");
//		//处理秒
//		if(second >= 0 && second <10) {
//			timeString.append("0");
//		}
//		timeString.append(second);
//		
//		return timeString.toString();
//	}
	
	//设计一个方法 还原以前的答案
	private void restoreButton() {
		//获取当前的题目的答案
		String answer = answers[nowNum];
		if(answer == null) {
			return ;
		}
		switch(answer) {
			case "A":
				aButton.setBackground(Color.green);
				break;
			case "B":
				bButton.setBackground(Color.green);
				break;
			case "C":
				cButton.setBackground(Color.green);
				break;
			case "D":
				cButton.setBackground(Color.green);
				break;
			default:
				this.clearOptionButtonColor();
				break;
		}
	}
	//设计一个方法那所有选项失效
	private void setOptionButtonEnabled(boolean key) {
		aButton.setEnabled(key);
		bButton.setEnabled(key);
		cButton.setEnabled(key);
		dButton.setEnabled(key);
		nextButton.setEnabled(key);
		
	}
	
	//设计一个方法  清除所有按钮颜色的方法
	private void clearOptionButtonColor() {
		aButton.setBackground(null);
		bButton.setBackground(null);
		cButton.setBackground(null);
		dButton.setBackground(null);
	}
	//设计展示图片的方法
	private ImageIcon drawImage(String path) {
		ImageIcon imageIcon = new ImageIcon(path);
		//设置image Icon对象内image属性
		imageIcon.setImage(imageIcon.getImage().getScaledInstance(280, 230, Image.SCALE_DEFAULT));
		//返回Icon 对象
		return imageIcon;
	}
	
	private void showQuestionAndPicture() {
		Question question = paper.get(nowNum);	//有三个属性 题干  答案 图片路径（可能未null）
		//获取当前question的图片路径
		String picture = question.getPicture();	//图片路径
		if(picture != null) {
			pictureLabel.setIcon(this.drawImage("src/com/img/" + picture));
		}else {//没有图片信息
			pictureLabel.setIcon(null);
		}
		//处理<br>标记
		examArea.setText((nowNum + 1) + "." +question.getTitle().replace("<br>", "\n"));
	}

}
























