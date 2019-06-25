package com.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.domain.User;
import com.jframe.examsystem.util.UserFileReader;

/**
 * 数据的持久化
 * 只负责数据的读写
 * 现阶段的内部方法 通常为I/O
 * @author RLe·奶
 *
 */
public class UserDao {

	static final String FilePath = "src/com/dbfile/User.txt";
	/**
	 * 写法一
	 */
//	public User selectOne(String account) {
//		User user = null;
//		FileReader fread;
//		try {
//			fread = new FileReader(FilePath);
//			BufferedReader reader = new BufferedReader(fread);
//			String message = reader.readLine();
//			while(message != null) {
//				String[] values = message.split("-");    //数组内  一个人的名字和密码
//				if(values[0].equals(account)) {
//					user = new User(values[0], values[1]);
//					break;
//				}
//				message = reader.readLine();
//			}
//			reader.close();
//			fread.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return user;
//	}
	
	
	/**
	 * 写法二 
	 * 在缓存流中读取
	 * @param account
	 * @return
	 */
	public User selectOne(String account) {
		
		return UserFileReader.getUser(account);
	}
	
	
}
