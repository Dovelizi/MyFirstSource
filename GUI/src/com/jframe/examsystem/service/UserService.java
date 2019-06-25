package com.jframe.examsystem.service;

import com.dao.UserDao;
import com.domain.User;
import com.jframe.examsystem.util.MySpring;

/**
 * 业务层
 * 负责处理读到的数据  负责处理业务逻辑产生的新数据
 * @author RLe·奶
 *
 */
public class UserService {
	//将new一个新对象的权力交给另一个类----->控制的反转
	private UserDao dao = MySpring.getBean("com.dao.UserDao");
	//设计一个方法---负责登陆
	public String login(String account, String password) {
		User user = dao.selectOne(account);
		if(null != user) {
			if(user.getPassword().equals(password)) {
				return "登陆成功";
			}	
		}
			return "用户名或者密码错误";	
	}
}
