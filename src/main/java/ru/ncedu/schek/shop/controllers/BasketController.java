package ru.ncedu.schek.shop.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.ncedu.schek.shop.entities.Phone;
import ru.ncedu.schek.shop.entities.User;
import ru.ncedu.schek.shop.repos.PhoneRepository;
import ru.ncedu.schek.shop.repos.UserRepository;

@Controller
@RequestMapping("/basket")
public class BasketController {
	@Autowired
	private UserRepository users;
	@Autowired
	private PhoneRepository phones;
	
	@GetMapping
    public String basket(Model model, Principal principal) {		
		User user = users.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("isAdmin", user.isAdmin());
		model.addAttribute("isUser", user.isUser());
        return "redirect:/basket/user?userId="+user.getId();
    }
	
	@RequestMapping(value = { "/deletefrombasket" }, method = RequestMethod.GET)
	public String deletephone(Model model, @RequestParam(name="phoneId")long phoneId, @RequestParam(name="userId")long userId) {
		User user = users.findById(userId).get();
		Optional<Phone> pre_phone = phones.findById(phoneId);
		Phone phone = pre_phone.get();
		user.deletePhone(phone);
		users.save(user);
		return "redirect:/basket/user?userId="+userId;
	}
	
	@GetMapping("/user")
    public String basket(Model model, @RequestParam(name="userId")long userId, Principal principal) {		
		User user = users.findById(userId).get();
		model.addAttribute("user", user);
	        
        User currUser = users.findByUsername(principal.getName());
        model.addAttribute("isAdmin", currUser.isAdmin());
		model.addAttribute("isUser", currUser.isUser());
        return "basket";
    }
}
