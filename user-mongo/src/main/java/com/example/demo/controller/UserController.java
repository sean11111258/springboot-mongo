package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.UserRequest;
import com.example.demo.model.UserResponse;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	private UserService userservice;

	// 增
	@PostMapping
	public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest request) {
		UserResponse user = userservice.addUser(request);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
				.toUri();

		return ResponseEntity.created(location).body(user);
	}

	// 刪
	@DeleteMapping("/{id}")
	public ResponseEntity deleteUser(@PathVariable int id) {
		userservice.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

	// 查--all
	@GetMapping
	public ResponseEntity<List<UserResponse>> getUsers() {
		List<UserResponse> users = userservice.getUserResponses();
		return ResponseEntity.ok(users);

	}

	// 改
	@PutMapping()
	public ResponseEntity<UserResponse> updateUser( @RequestBody UserRequest request) {
		UserResponse user = userservice.updateUser(request);
		return ResponseEntity.ok(user);
	}

	// 查--id
	@RequestMapping("/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable("id") int id) {
		UserResponse user = userservice.getUserResponse(id);
		return ResponseEntity.ok(user);
	}

}
