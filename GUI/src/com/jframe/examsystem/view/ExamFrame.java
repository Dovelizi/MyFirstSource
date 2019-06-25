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

	//��ȡQuestionService����
	private QuestionService service = MySpring.getBean("com.jframe.examsystem.service.QuestionService");
	//ͨ��service����getPaper������ȡһ��������ɵ��Ծ�
	private ArrayList<Question> paper = service.getPaper(8);
	//����һ���洢�𰸵�����
	private String[] answers = new String[paper.size()];
	//������������  �ֱ�����Ҳ�����ı仯
	private int nowNum = 0;									//��¼��ǰ���
	private int totleCount = paper.size();					//��¼��Ŀ����
	private int answerCount = 0;							//��¼�Ѿ�������
	private int unanswerCount = totleCount - answerCount;	//δ������
	//����һ���̶߳��� ����ʱ��仯
	private TimeControlThread timeControlThread = new TimeControlThread();
	//����һ������  ������¼ʱ��(�Է���Ϊ��λ)
	private int time = 10;
	
	//*************************����Ϊ����Ļ���*******************************
	public ExamFrame() {
		this.init();
	}
	public ExamFrame(String title) {
		super(title);
		this.init();	
	}
	//�滭��ҳ��
	private JPanel mainPanel = new JPanel();		//��������
	private JPanel messagePanel = new JPanel();		//������Ϣ��ʾ��
	private JPanel buttonPanel = new JPanel();		//��Ťȥ
	
	//
	private JTextArea examArea = new JTextArea();				//���������ı���
	private JScrollPane scrollPane = new JScrollPane(examArea);	//������
	//
	private JLabel pictureLabel = new JLabel();					//ͼƬչʾ��
	private JLabel currLabel = new JLabel("��ǰ��ţ�");			//��ʾ��ǰ���
	private JLabel totleCountLabel = new JLabel("��Ŀ������");		//��ʾ��������
	private JLabel answerCountLabel = new JLabel("�Ѵ�������");	//��ʾ�Ѿ�������
	private JLabel unanswerCountLabel = new JLabel("δ������:");	//��ʾδ������
	//����Ҫ��ʾ�ľ�����ֵ
	private JTextField currField = new JTextField();
	private JTextField totleCountField = new JTextField();
	private JTextField answerCountField = new JTextField();
	private JTextField unanswerCountField = new JTextField();
	//��ʾʱ����
	private JLabel timeLabel = new JLabel("ʣ�����ʱ�䣺");
	private JLabel realTimeLabel = new JLabel("00:00:00");
	//��ť��
	private JButton aButton = new JButton("A");
	private JButton bButton = new JButton("B");
	private JButton cButton = new JButton("C");
	private JButton dButton = new JButton("D");
	private JButton prevButton = new JButton("��һ��");
	private JButton nextButton = new JButton("��һ��");
	private JButton summitButton = new JButton("�� ��");
	
	@Override
	protected void setFontAndOn() {
		//�����Զ��岼�ֹ���
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.LIGHT_GRAY);
		//����messsage����
		messagePanel.setLayout(null);
		messagePanel.setBounds(680, 10, 300, 550);
		messagePanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		//���ð�ť����
		buttonPanel.setLayout(null);
		buttonPanel.setBounds(16, 470, 650, 90);
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.green));
		//��������
		scrollPane.setBounds(16, 10, 650, 450);
		examArea.setFont(new FontUIResource("����", Font.BOLD, 24));
		//Ĭ�����û�һ��½��չʾ��һ��
		
		examArea.setEnabled(false);									//�ı����е����ݲ��ܸ���	
		//����message�����ÿһ�������λ��
		//��һ��
		pictureLabel.setBounds(10, 10, 280, 230);
		pictureLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		pictureLabel.setIcon(null); 	//չʾͼƬ��Ϣ
		currLabel.setBounds(20, 270, 140, 30);
		currLabel.setFont(new Font("����", Font.PLAIN, 25));
		currField.setBounds(150, 270, 100, 30);
		currField.setFont(new Font("����", Font.BOLD, 20));
		currField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		currField.setEnabled(false);
		currField.setHorizontalAlignment(JTextField.CENTER);
		//�ڶ���
		totleCountLabel.setBounds(20, 310, 140, 30);
		totleCountLabel.setFont(new Font("����", Font.PLAIN, 25));
		totleCountField.setBounds(150, 310, 100, 30);
		totleCountField.setFont(new Font("����", Font.BOLD, 20));
		totleCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		totleCountField.setEnabled(false);
		totleCountField.setHorizontalAlignment(JTextField.CENTER);
		//������
		answerCountLabel.setBounds(20, 350, 140, 30);
		answerCountLabel.setFont(new Font("����", Font.PLAIN, 25));
		answerCountField.setBounds(150, 350, 100, 30);
		answerCountField.setFont(new Font("����", Font.BOLD, 20));
		answerCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		answerCountField.setEnabled(false);
		answerCountField.setHorizontalAlignment(JTextField.CENTER);
		//������
		unanswerCountLabel.setBounds(20, 390, 140, 30);
		unanswerCountLabel.setFont(new Font("����", Font.PLAIN, 25));
		unanswerCountField.setBounds(150, 390, 100, 30);
		unanswerCountField.setFont(new Font("����", Font.BOLD, 20));
		unanswerCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		unanswerCountField.setEnabled(false);
		unanswerCountField.setHorizontalAlignment(JTextField.CENTER);
		//������
		timeLabel.setBounds(90, 470, 150, 30);
		timeLabel.setFont(new Font("����", Font.PLAIN, 20));
		timeLabel.setForeground(Color.green);
		realTimeLabel.setBounds(108, 500, 150, 30);
		realTimeLabel.setFont(new Font("����", Font.BOLD, 20));
		realTimeLabel.setForeground(Color.green);
		//��ť�Ĳ���
		aButton.setBounds(40, 10, 120, 30);
		bButton.setBounds(190, 10, 120, 30);
		cButton.setBounds(340, 10, 120, 30);
		dButton.setBounds(490, 10, 120, 30);	
		prevButton.setBounds(40, 50, 100, 30);
		nextButton.setBounds(510, 50, 100, 30);
		summitButton.setBounds(276, 50, 120, 30);
		summitButton.setForeground(Color.red);
		summitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));   //�ı������ʽ
	
		//++++++++++++++++++++++++++++++++++++++++++
		//չʾ��һ��
		this.showQuestionAndPicture();
		//���������Ҳ��message�е����-----����ֵ
		currField.setText(nowNum + 1 + "");
		totleCountField.setText(totleCount + "");
		answerCountField.setText(answerCount + "");
		unanswerCountField.setText(unanswerCount + "");
		
	}

	@Override
	protected void addElement() {
		messagePanel.add(pictureLabel);
		/**�������ڻ�����*/
		messagePanel.add(currLabel);
		messagePanel.add(currField);
		//�����
		messagePanel.add(totleCountLabel);
		messagePanel.add(totleCountField);
		//�����
		messagePanel.add(answerCountLabel);
		messagePanel.add(answerCountField);
		//�����
		messagePanel.add(unanswerCountLabel);
		messagePanel.add(unanswerCountField);
		//�����
		messagePanel.add(timeLabel);
		messagePanel.add(realTimeLabel);
		//��Ӱ�ť����ť������
		buttonPanel.add(aButton);
		buttonPanel.add(bButton);
		buttonPanel.add(cButton);
		buttonPanel.add(dButton);
		buttonPanel.add(prevButton);
		buttonPanel.add(nextButton);
		buttonPanel.add(summitButton);
		//�����������������С����
		mainPanel.add(scrollPane);
		mainPanel.add(messagePanel);
		mainPanel.add(buttonPanel);
		//�����������뵽JFrame�ؼ���
		this.add(mainPanel);
		
	}

	@Override
	protected void addListener() {
		//����һ������������  �����ύ��ť
		summitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//���û���ʾ�Ƿ�ִ�иĲ���
				
				//����ʱ��ʱ��ֹͣ
				//timeControlThread.stop();
				timeControlThread.stopTimeThread(false);
				//���а�ťʧЧ
				ExamFrame.this.setOptionButtonEnabled(false);
				ExamFrame.this.clearOptionButtonColor();
				prevButton.setEnabled(false);
				//��ӡ���ճɼ���
				float score = ExamFrame.this.checkPaper();
				//JOptionPane.showConfirmDialog(ExamFrame.this, "�Ƿ񽻾�");
				int result = JOptionPane.showConfirmDialog(ExamFrame.this, "�Ƿ񽻾�");
				if(result == 0) {
					JOptionPane.showMessageDialog(ExamFrame.this, "�������յ÷�Ϊ��\n"+ExamFrame.this.checkPaper());
					examArea.setText("���Խ�����\n �������ճɼ�Ϊ" + score);
				}else {
					timeControlThread.stopTimeThread(true);
				}
			}
		});
		
		
		//������һ��İ�ť�ļ�����
		prevButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//�����а�ť�Ŀ�ѡ���Ը�Ϊtrue
				ExamFrame.this.setOptionButtonEnabled(true);
				//������а�ť����ɫ
				ExamFrame.this.clearOptionButtonColor();
				//��ǰ��ż���һ��
				nowNum--;
				
				//�����ǰ���Ϊ0 �������һ�ⰴť
				if(nowNum == 0) {
					prevButton.setEnabled(false);
				}
				//��ʾ��ǰ�˻ص���Ŀ��ͬʱ��Ը��ǰ��ѡ��
				ExamFrame.this.restoreButton();
				ExamFrame.this.showQuestionAndPicture();
				//�޸��Ҳ����ֵ
				currField.setText(nowNum + 1 + "");
				answerCountField.setText(--answerCount + "");
				unanswerCountField.setText(++unanswerCount  + "");
			}
		});
		
		//������һ�İ�ť�ļ�����
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//��������а�ť����ɫ
				ExamFrame.this.clearOptionButtonColor();
				//��ǰ��ż�1 
				nowNum++;
				//�жϵ�ǰ������Ƿ�δ���һ��
				if(nowNum == totleCount) {//�Ѿ�������ȫ����
					//��ʱ���������а�ť���ܵ��
					ExamFrame.this.setOptionButtonEnabled(false);
					prevButton.setEnabled(true);
					examArea.setText("ȫ����Ŀ�Ѿ��ش���ϣ�\n���������������ύ��ť�����Ծ���ύ");
				}else {
					//�����Լ���װ����ʾ��Ŀ�ķ���
					ExamFrame.this.showQuestionAndPicture();
					//�޸��Ҳ����ʾ��Ϣ
					currField.setText(nowNum + 1 + "");	
					//����û��ظ�����������Ҫ�����»�ȡ��ѡ��
					ExamFrame.this.restoreButton();
				}
				//�Ѿ���������δ���
				answerCountField.setText(++answerCount + "");
				unanswerCountField.setText(--unanswerCount  + "");
			}
		});
		
		
		//����һ��������  --- �����ĸ�ѡ��İ�ť
		ActionListener optionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			//���֮ǰ�İ�ť����ɫ
			ExamFrame.this.clearOptionButtonColor();
			//��ȡ��������ĸ���ť
			JButton button = (JButton)e.getSource();		
			//����ɫ�仯һ��
			button.setBackground(Color.green);
			//����ǰ��ť��ѡ��洢��answers������
			answers[nowNum] = button.getText();
			}
		};
		//�ֱ�����ĸ���ť
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
		//�ڳ�ʼ��ͼ������ʱ�򵹼�ʱ���߳�׼������
		timeControlThread.start();
		
	}
	
	//*****************************չʾ��Ŀ�ķ���************************************
	
	//���һ���������ڲ��ࣩ ����ʱ��ı仯
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
			//��ʱ��ת��Ϊ��Ӧ��ʱ���ʽ��չʾ����
			//realTimeLabel.setText(ExamFrame.this.transformTime(time));
			//��ͣ�ı仯ʱ��
			while(falg) {
				StringBuffer timeString = new StringBuffer();
				//����Сʱ
				if(hour >= 0 && hour <10) {
					timeString.append("0");
				}
				timeString.append(hour);
				timeString.append(":");
				//�������
				if(minute >= 0 && minute <10) {
					timeString.append("0");
				}
				timeString.append(minute);
				timeString.append(":");
				//������
				if(second >= 0 && second <10) {
					timeString.append("0");
				}
				timeString.append(second);
				//չʾƴ���Ժ��ʱ��
				/**
				 * ���ʱ��ĵ���ʱ����Ļ� �����ٵ���util���е�һ��С�߳�
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
				//�ı�ʱ��
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
							JOptionPane.showMessageDialog(ExamFrame.this, "����ʱ�䵽���뽻��");
							//�����ύ�����а�ť������
							ExamFrame.this.setOptionButtonEnabled(false);
							prevButton.setEnabled(false);
							//��ʱ����ɫ���
							realTimeLabel.setForeground(Color.red);
						}
						break;
					}
				}
			}
		}
	}
	//^^^^^^^^^^^^^^^^^^^^^^^����ɼ�^^^^^^^^^^^^^^^^^^^^^^^^
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
//		//����Сʱ
//		if(hour >= 0 && hour <10) {
//			timeString.append("0");
//		}
//		timeString.append(hour);
//		timeString.append(":");
//		//�������
//		if(minute >= 0 && minute <10) {
//			timeString.append("0");
//		}
//		timeString.append(minute);
//		timeString.append(":");
//		//������
//		if(second >= 0 && second <10) {
//			timeString.append("0");
//		}
//		timeString.append(second);
//		
//		return timeString.toString();
//	}
	
	//���һ������ ��ԭ��ǰ�Ĵ�
	private void restoreButton() {
		//��ȡ��ǰ����Ŀ�Ĵ�
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
	//���һ������������ѡ��ʧЧ
	private void setOptionButtonEnabled(boolean key) {
		aButton.setEnabled(key);
		bButton.setEnabled(key);
		cButton.setEnabled(key);
		dButton.setEnabled(key);
		nextButton.setEnabled(key);
		
	}
	
	//���һ������  ������а�ť��ɫ�ķ���
	private void clearOptionButtonColor() {
		aButton.setBackground(null);
		bButton.setBackground(null);
		cButton.setBackground(null);
		dButton.setBackground(null);
	}
	//���չʾͼƬ�ķ���
	private ImageIcon drawImage(String path) {
		ImageIcon imageIcon = new ImageIcon(path);
		//����image Icon������image����
		imageIcon.setImage(imageIcon.getImage().getScaledInstance(280, 230, Image.SCALE_DEFAULT));
		//����Icon ����
		return imageIcon;
	}
	
	private void showQuestionAndPicture() {
		Question question = paper.get(nowNum);	//���������� ���  �� ͼƬ·��������δnull��
		//��ȡ��ǰquestion��ͼƬ·��
		String picture = question.getPicture();	//ͼƬ·��
		if(picture != null) {
			pictureLabel.setIcon(this.drawImage("src/com/img/" + picture));
		}else {//û��ͼƬ��Ϣ
			pictureLabel.setIcon(null);
		}
		//����<br>���
		examArea.setText((nowNum + 1) + "." +question.getTitle().replace("<br>", "\n"));
	}

}
























