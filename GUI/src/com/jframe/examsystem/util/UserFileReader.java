package com.jframe.examsystem.util;
/**
 * 目的为了增加一个缓存机制
 * 在程序执行的时候将User.txt文件中的所有信息 一次性读取出来
 * 提高数据读取的性能
 * @author RLe·奶
 *
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.domain.User;

public class UserFileReader {
	static final String FilePath = "src/com/dbfile/User.txt";
	//创建集合--->充当一个缓存
	//添加静态属性是因为 保证Hash Map只创建一次
	private static HashMap<String, User> userBox = new HashMap<>();   
	//使用静态属性是为了不用创建对象  直接用类名
	public static User getUser(String account){
		return userBox.get(account);
	}
	/**
	 * 程序加载块
	 */
	//使用静态属性是因为在希望 在创建hashmap后 直接执行该代码块
	static{
		BufferedReader reader = null;
		String user = null;
		try {
			reader = new BufferedReader(new FileReader(FilePath));
			user = reader.readLine();
			while(null != user) {
				String[] values = user.split("-");
				//System.out.println("账户： " + values[0] + "  密码：" +values[1]);
				userBox.put(values[0], new User(values[0], values[1]));
				user = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(user == null) {
					reader.close();
				}
					} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
