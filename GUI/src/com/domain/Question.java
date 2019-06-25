package com.domain;

/**
 * domain 实体 存储文件中的题目 增强可读性
 * 
 * @author RLe·奶
 *
 */
public class Question {

	private String title;
	private String answer;
	private String picture;

	public Question() {

	}

	public Question(String title, String answer) {
		this.title = title;
		this.answer = answer;
	}
	public Question(String title, String answer, String pictrue) {
		this.title = title;
		this.answer = answer;
		this.picture = pictrue;
	}
	
	//重写Question中的equals
	@Override
	public boolean equals(Object obj) {
		 if(this == obj) {
			 return true;
		 }
		 if(obj instanceof Question) {
			 Question anotherQuestion = (Question)obj;
			 String thistitle = this.title.substring(0, this.title.indexOf("<br>"));
			 String anothertitle = anotherQuestion.title.substring(0, anotherQuestion.title.indexOf("<br>"));
			 if(thistitle.equals(anotherQuestion)) {
				 return true;
			 }
		 }
		return false;
	}
	
	//重写hashCode
	@Override
	public int hashCode() {
		String thistitle = this.title.substring(0, this.title.indexOf("<br>"));
		return thistitle.hashCode();
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	

}
