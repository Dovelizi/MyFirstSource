package com.jframe.examsystem.service;
/**
 * ҵ���߼���
 * ��service����Ҫ��һ���ײ�dao��֧��
 * @author RLe����
 *
 */

import java.util.ArrayList;

import com.dao.QuestionDao;
import com.domain.Question;
import com.jframe.examsystem.util.MySpring;

public class QuestionService {

	private QuestionDao dao = MySpring.getBean("com.dao.QuestionDao");
	
	//��ȡ�Ծ�
	public ArrayList<Question> getPaper(int count){
		return dao.getPeper(count);
	}

}

