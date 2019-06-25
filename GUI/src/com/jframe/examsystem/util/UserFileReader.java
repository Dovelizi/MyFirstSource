package com.jframe.examsystem.util;
/**
 * Ŀ��Ϊ������һ���������
 * �ڳ���ִ�е�ʱ��User.txt�ļ��е�������Ϣ һ���Զ�ȡ����
 * ������ݶ�ȡ������
 * @author RLe����
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
	//��������--->�䵱һ������
	//��Ӿ�̬��������Ϊ ��֤Hash Mapֻ����һ��
	private static HashMap<String, User> userBox = new HashMap<>();   
	//ʹ�þ�̬������Ϊ�˲��ô�������  ֱ��������
	public static User getUser(String account){
		return userBox.get(account);
	}
	/**
	 * ������ؿ�
	 */
	//ʹ�þ�̬��������Ϊ��ϣ�� �ڴ���hashmap�� ֱ��ִ�иô����
	static{
		BufferedReader reader = null;
		String user = null;
		try {
			reader = new BufferedReader(new FileReader(FilePath));
			user = reader.readLine();
			while(null != user) {
				String[] values = user.split("-");
				//System.out.println("�˻��� " + values[0] + "  ���룺" +values[1]);
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
