package com.citi.model;

public class Student {
	private int id;
	private String firstName;
	private String lastName;
	private String selection;

	public Student() {
		System.out.println("default constructor called");
	}
	
	
	public Student(int id, String firstName, String lastName, String selection) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.selection = selection;
		System.out.println("paramaterized constructor called");
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

}
