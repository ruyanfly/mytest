package com.ruyanfly.entity;

public class Postgraduate {
	//mybatis构成SQL语句时，传入类型为int值为0时，会被认为是空字符，如果使用int，传递给后台时候都会默认传入0
	private Integer id;
	private String name;
	private String age;
	private String ProfessorID;
	
	public void setID(Integer id) {
		this.id = id;
	}
	public Integer getID() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	public String getAge() {
		return this.age;
	}
	
	public void setSex(String sex) {
		this.ProfessorID = sex;
	}
	public String getSex() {
		return this.ProfessorID;
	}

}