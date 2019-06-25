package com.jframe.examsystem.util;

import java.util.HashMap;

/**
 * Ϊ�˹������Ĳ���
 * ����Ŀ���Ȩ������ǰ���ฺ��   IOC���Ʒ�ת
 * ���������й�ʵ���˶���ĵ���
 * @author RLe����
 *
 */
public class MySpring {

	//����  Ϊ�˴洢���б������ߵĶ���
	public static HashMap<String, Object> beanBox = new HashMap<>();
	//���һ������  ��ȡ�κ�һ����Ķ���
	public static  <T>T getBean(String className) { //ͨ��<T>T��֤ ����ֵΪ�Լ������������
		T obj = null;
		try {//��֤ ��ֻ֤��һ������
			obj = (T)beanBox.get(className);	//�����ڼ�����ȡ  �������������ֱ�Ӹ�ֵ ��֮�����´����¶���
			if(obj == null) {
				//ͨ��������ֻ�ȡclass
				Class cClass = Class.forName(className);
				//ͨ������������һ������
				obj = (T)cClass.newInstance();
				//���¶�����뼯��
				beanBox.put(className, obj);
			}	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}
