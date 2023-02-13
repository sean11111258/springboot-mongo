package com.example.demo.model;

import org.springframework.stereotype.Component;

import com.mongodb.lang.NonNull;

import jakarta.persistence.Id;

@Component
public class User {
	@Id
	private int id;
	@NonNull
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "user [id=" + id + ", name=" + name + "]";
	}

	public User(String name) {
		this.name = name;
	}

	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public User() {
	}
}
