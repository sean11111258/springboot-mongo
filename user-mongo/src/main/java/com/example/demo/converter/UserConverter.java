package com.example.demo.converter;

import com.example.demo.model.User;
import com.example.demo.model.UserRequest;
import com.example.demo.model.UserResponse;

public class UserConverter {

	private UserConverter() {
	}
	public static UserResponse toUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());

        return response;
    }

    public static User toUser(UserRequest request) {
        User user = new User();
        user.setId(request.getId());
        user.setName(request.getName());

        return user;
    }
}
