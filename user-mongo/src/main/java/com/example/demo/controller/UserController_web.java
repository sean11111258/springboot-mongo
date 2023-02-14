package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.converter.UserConverter;
import com.example.demo.model.User;
import com.example.demo.model.UserRequest;
import com.example.demo.service.UserService;

@Controller
public class UserController_web {
	@Autowired
	UserService userservice;

	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}

	@RequestMapping("/addUser")
	public ModelAndView addUser(UserRequest request) {
		ModelAndView mv = new ModelAndView("showUser.jsp");
		User user=UserConverter.toUser(request);
		
		userservice.addUser(request);
		mv.addObject(new User() {
			@Override
			public String toString() {
				return user.toString() + " is added";
			}
		});
		return mv;
	}

	@RequestMapping("/getUser")
	public ModelAndView getUser(@RequestParam int id) {
		ModelAndView mv = new ModelAndView("showUser.jsp");
		User user = userservice.getUser(id);
		mv.addObject(user);
		return mv;
	}

	@RequestMapping("/deleteUser")
	public ModelAndView deleteUser(@RequestParam int id) {
		ModelAndView mv = new ModelAndView("showUser.jsp");
		User user = userservice.getUser(id);

		userservice.deleteUser(user.getId());
		mv.addObject(new User() {
			@Override
			public String toString() {
				return user.toString() + " is deleted";
			}
		});
		return mv;
	}

}
