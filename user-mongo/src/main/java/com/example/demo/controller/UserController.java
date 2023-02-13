package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserRepo;
import com.example.demo.model.User;

@RestController
public class UserController {
	@Autowired
	UserRepo repo;
	
    // 增
    @PostMapping(path = "/User", consumes = { "application/json" })
    public User addUser(@RequestBody User user) {
        repo.insert(user);
        return user;
    }

    // 刪
    @DeleteMapping("/User/{id}")
    public User deleteUser(@PathVariable int id) {
        User u = repo.findById(id).get();
        repo.delete(u);
        return u;
    }

    // 查--all
    @GetMapping(path = "/Users")
    public List<User> getUsers() {
        return repo.findAll();
    }

    // 改
    @PutMapping(path = "/User", consumes = { "application/json" })
    public User updateUser(@RequestBody User user) {
        repo.save(user);
        return user;
    }

    // 查--id
    @RequestMapping("/User/{id}")
    public Optional<User> getUser(@PathVariable("id") int id) {
        return repo.findById(id);
    }

}
