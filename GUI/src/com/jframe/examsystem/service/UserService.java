package com.jframe.examsystem.service;

import com.dao.UserDao;
import com.domain.User;
import com.jframe.examsystem.util.MySpring;

/**
 * ҵ���
 * ���������������  ������ҵ���߼�������������
 * @author RLe����
 *
 */
public class UserService {
	//��newһ���¶����Ȩ��������һ����----->���Ƶķ�ת
	private UserDao dao = MySpring.getBean("com.dao.UserDao");
	//���һ������---�����½
	public String login(String account, String password) {
		User user = dao.selectOne(account);
		if(null != user) {
			if(user.getPassword().equals(password)) {
				return "��½�ɹ�";
			}	
		}
			return "�û��������������";	
	}
}
