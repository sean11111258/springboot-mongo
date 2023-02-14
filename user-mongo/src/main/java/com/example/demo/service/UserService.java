package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.converter.UserConverter;
import com.example.demo.dao.UserRepo;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.User;
import com.example.demo.model.UserRequest;
import com.example.demo.model.UserResponse;

@Service
public class UserService {
	@Autowired
	private UserRepo repo;

	public UserService() {
	}

	public UserService(UserRepo repo) {
		this.repo = repo;
	}
	
	public User getUser(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find product."));
    }

	// 查-id
	public UserResponse getUserResponse(int id) {
		User user = repo.findById(id).orElseThrow(() -> new NotFoundException("Can't find product."));
		return UserConverter.toUserResponse(user);
	}

	// 增-id
	public UserResponse addUser(UserRequest request) {
		User user = UserConverter.toUser(request);

		repo.insert(user);
		return UserConverter.toUserResponse(user);
	}
	// 查
	public UserResponse updateUser(UserRequest request) {
		User user = UserConverter.toUser(request);

		repo.save(user);
		return UserConverter.toUserResponse(user);
	}
	// 刪-id
	public void deleteUser(int id) {
		repo.deleteById(id);
	}

	public List<UserResponse> getUserResponses() {
		List<User> user = repo.findAll();
		return user.stream().map(UserConverter::toUserResponse).collect(Collectors.toList());
	}

}
