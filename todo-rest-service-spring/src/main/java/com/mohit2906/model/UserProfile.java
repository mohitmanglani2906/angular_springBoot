package com.mohit2906.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class UserProfile 
{	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	private String name;
	
	@Column(name = "email")
	private String email;
	
	private String password;
	
	
	private Integer age;
	
	private Timestamp dateOfBirth;
	
	public UserProfile() {
		super();
	}

	public UserProfile(Long id, String name, String email, String password, Integer age, Timestamp dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.age = age;
		this.dateOfBirth = dateOfBirth;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", age="
				+ age + ", dateOfBirth=" + dateOfBirth + "]";
	}
		
	
}
