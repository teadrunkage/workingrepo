package ru.ncedu.schek.shop.controllers;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ru.ncedu.schek.shop.entities.Role;
import ru.ncedu.schek.shop.entities.User;
import ru.ncedu.schek.shop.repos.UserRepository;

@Controller
public class RegistrationController {
	@Autowired
	private UserRepository users;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@GetMapping("/registration") 
	public String registration() {
		return "registration";
	}
	
	@PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = users.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("checkDb", true);
            return "registration";
        }
        
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        user.setActive(true);
    	Set<Role> roles = new HashSet<Role>();
		user.setRoles(roles);
		user.addRole(Role.USER);
        users.save(user);

        return "redirect:/login";
    }
	
}
