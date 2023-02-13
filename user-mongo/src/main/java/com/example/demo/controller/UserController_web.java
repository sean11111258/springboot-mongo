package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.UserRepo;
import com.example.demo.model.User;

@Controller
public class UserController_web {
	@Autowired
	UserRepo repo;

	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}

	@RequestMapping("/addUser")
	public ModelAndView addUser(User user) {
		ModelAndView mv = new ModelAndView("showUser.jsp");
		repo.insert(user);
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
		User user = repo.findById(id).orElse(new User() {
			@Override
			public String toString() {
				return "No Such User";
			}
		});
		mv.addObject(user);
		return mv;
	}

	@RequestMapping("/deleteUser")
	public ModelAndView deleteUser(@RequestParam int id) {
		ModelAndView mv = new ModelAndView("showUser.jsp");
		Optional<User> user = repo.findById(id);
		if (user.isEmpty()) {
			mv.addObject(new User() {
				@Override
				public String toString() {
					return "No Such User";
				}
			});
		}

		mv.addObject(new User() {
			@Override
			public String toString() {
				return user.get().toString() + " is deleted";
			}
		});
		repo.delete(user.get());
		return mv;
	}

}
