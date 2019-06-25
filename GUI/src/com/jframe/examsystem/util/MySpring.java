package com.jframe.examsystem.util;

import java.util.HashMap;

/**
 * 为了管理对象的产生
 * 对象的控制权交给当前的类负责   IOC控制反转
 * 生命周期托管实现了对象的单例
 * @author RLe·奶
 *
 */
public class MySpring {

	//属性  为了存储所有被管理者的对象
	public static HashMap<String, Object> beanBox = new HashMap<>();
	//设计一个方法  获取任何一个类的对象
	public static  <T>T getBean(String className) { //通过<T>T保证 返回值为自己所定义的类型
		T obj = null;
		try {//保证 保证只有一个对象
			obj = (T)beanBox.get(className);	//首先在集合中取  如果集合中有则直接赋值 反之就重新创建新对象
			if(obj == null) {
				//通过类的名字获取class
				Class cClass = Class.forName(className);
				//通过反射来产生一个对象
				obj = (T)cClass.newInstance();
				//将新对象存入集合
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
