package com.jframe.examsystem.util;
/**
 * Ŀ���ڳ��� ִ��ʱ���ļ����е���Ŀ һ���Զ�����
 * @author RLe����
 *
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import com.domain.Question;

public class QuestionFileReader {

	static final String FilePath = "src/com/dbfile/Questions.txt";
	private HashSet<Question> questionBox= new HashSet<>();
	
	{
		BufferedReader reader = null;
		String message = null;
		try {
			reader = new BufferedReader(new FileReader(FilePath));
			message = reader.readLine();
			while(message != null) {
				String[] values = message.split("#");
				if(values.length == 2) {//��Ŀ��û��ͼƬ
					questionBox.add(new Question(values[0], values[1]));
				}else if(values.length == 3) {//����ͼƬ
					questionBox.add(new Question(values[0], values[1], values[2]));
				}
				message = reader.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(message == null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public HashSet<Question> getQuestionBox(){
		return questionBox;
	}
	
	
	
}
