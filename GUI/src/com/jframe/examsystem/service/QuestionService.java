package com.jframe.examsystem.service;
/**
 * 业务逻辑层
 * 在service层需要有一个底层dao的支持
 * @author RLe·奶
 *
 */

import java.util.ArrayList;

import com.dao.QuestionDao;
import com.domain.Question;
import com.jframe.examsystem.util.MySpring;

public class QuestionService {

	private QuestionDao dao = MySpring.getBean("com.dao.QuestionDao");
	
	//获取试卷
	public ArrayList<Question> getPaper(int count){
		return dao.getPeper(count);
	}

}

