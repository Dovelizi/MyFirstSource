package com.dao;
/**
 * 也用使用缓存  来读题
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import com.domain.Question;
import com.jframe.examsystem.util.MySpring;
import com.jframe.examsystem.util.QuestionFileReader;

/**
 * 主要功能为读取文件---读题
 * 题库10题
 * 生成5道 --- 传参5
 * 放回值-----ArrayList<Question>
 * @author RLe·奶
 *
 */
public class QuestionDao {

	//获取缓存对象
	private QuestionFileReader reader = MySpring.getBean("com.jframe.examsystem.util.QuestionFileReader");
	//将hashset 转化为list集合  目的是为了在QuestionFileReader中随机抽取一定数量的题
	private ArrayList<Question> questionBank = new ArrayList(reader.getQuestionBox());
	
	public ArrayList<Question> getPeper(int count){
		//一行一行的读取文件（题目）
		//解析在读取后的题目的答案  创建Question对象
		//让抽取的题目不相同 用Set来达到选取的题目不重复   HashSet(equals hashCode)  TreeSet(compareTo)
		
		HashSet<Question> paper = new HashSet<>();			//用来存储最终的试卷题目
		while(paper.size() != count) {
			Random r = new Random();
			int index = r.nextInt(this.questionBank.size());
			paper.add(this.questionBank.get(index));
		}
		return new ArrayList<Question>(paper);
	}
}
