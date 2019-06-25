package com.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.domain.User;
import com.jframe.examsystem.util.UserFileReader;

/**
 * ���ݵĳ־û�
 * ֻ�������ݵĶ�д
 * �ֽ׶ε��ڲ����� ͨ��ΪI/O
 * @author RLe����
 *
 */
public class UserDao {

	static final String FilePath = "src/com/dbfile/User.txt";
	/**
	 * д��һ
	 */
//	public User selectOne(String account) {
//		User user = null;
//		FileReader fread;
//		try {
//			fread = new FileReader(FilePath);
//			BufferedReader reader = new BufferedReader(fread);
//			String message = reader.readLine();
//			while(message != null) {
//				String[] values = message.split("-");    //������  һ���˵����ֺ�����
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
	 * д���� 
	 * �ڻ������ж�ȡ
	 * @param account
	 * @return
	 */
	public User selectOne(String account) {
		
		return UserFileReader.getUser(account);
	}
	
	
}
