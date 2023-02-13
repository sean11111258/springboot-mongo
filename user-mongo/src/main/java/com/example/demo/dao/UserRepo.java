package com.example.demo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.User;

public interface UserRepo extends MongoRepository<User, Integer> {
	List<User> findByName(String name);

    List<User> findByidGreaterThan(int id);

    List<User> findByNameOrderById(String name);
}
