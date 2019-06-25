package com.dao;
/**
 * Ҳ��ʹ�û���  ������
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import com.domain.Question;
import com.jframe.examsystem.util.MySpring;
import com.jframe.examsystem.util.QuestionFileReader;

/**
 * ��Ҫ����Ϊ��ȡ�ļ�---����
 * ���10��
 * ����5�� --- ����5
 * �Ż�ֵ-----ArrayList<Question>
 * @author RLe����
 *
 */
public class QuestionDao {

	//��ȡ�������
	private QuestionFileReader reader = MySpring.getBean("com.jframe.examsystem.util.QuestionFileReader");
	//��hashset ת��Ϊlist����  Ŀ����Ϊ����QuestionFileReader�������ȡһ����������
	private ArrayList<Question> questionBank = new ArrayList(reader.getQuestionBox());
	
	public ArrayList<Question> getPeper(int count){
		//һ��һ�еĶ�ȡ�ļ�����Ŀ��
		//�����ڶ�ȡ�����Ŀ�Ĵ�  ����Question����
		//�ó�ȡ����Ŀ����ͬ ��Set���ﵽѡȡ����Ŀ���ظ�   HashSet(equals hashCode)  TreeSet(compareTo)
		
		HashSet<Question> paper = new HashSet<>();			//�����洢���յ��Ծ���Ŀ
		while(paper.size() != count) {
			Random r = new Random();
			int index = r.nextInt(this.questionBank.size());
			paper.add(this.questionBank.get(index));
		}
		return new ArrayList<Question>(paper);
	}
}
