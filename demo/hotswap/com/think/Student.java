package com.think;

public class Student
{
  public int id;
  public String name;
  
  public Student()
  {
    this.id = 9527;
    this.name = "Gavin";
  }

  public void hello() {
	this.id = 2000;
	this.name = "Peter";
  }
  
  public String toString()
  {
    return "Student [id=" + this.id + ", name=" + this.name + "]";
  }
}
