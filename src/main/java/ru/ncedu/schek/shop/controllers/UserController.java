package ru.ncedu.schek.shop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.ncedu.schek.shop.entities.Role;
import ru.ncedu.schek.shop.entities.User;
import ru.ncedu.schek.shop.repos.UserRepository;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {;
	@Autowired
	private UserRepository users;
	private boolean deletingAdmin = false;
	
	@RequestMapping(value = { "/users" }, method = RequestMethod.GET)
	public String users(Model model) {
		Iterable<User> allusers = users.findAll();
		model.addAttribute("users", allusers);
		model.addAttribute("deletingAdmin", deletingAdmin);
		deletingAdmin = false;
		return "users";
	}
	
	@GetMapping("/setAdmin")
    public String setAdmin(Model model, @RequestParam(name="userId")long userId) {		
		User user = users.findById(userId).get();
		user.addRole(Role.ADMIN);
		users.save(user);
        return "redirect:/users";
    }
	
	@GetMapping("/setUser")
    public String setUser(Model model, @RequestParam(name="userId")long userId) {		
		User user = users.findById(userId).get();
		user.addRole(Role.USER);
		users.save(user);
        return "redirect:/users";
    }
	
	@GetMapping("/rmAdmin")
    public String rmAdmin(Model model, @RequestParam(name="userId")long userId) {		
		User user = users.findById(userId).get();
		if (user.getId() != 1) {
			user.rmRole(Role.ADMIN);
		} else {
			deletingAdmin = true;
		}
		users.save(user);
        return "redirect:/users";
    }
	
	@GetMapping("/rmUser")
    public String rmUser(Model model, @RequestParam(name="userId")long userId) {		
		User user = users.findById(userId).get();
		user.rmRole(Role.USER);
		users.save(user);
        return "redirect:/users";
    }
	
}
