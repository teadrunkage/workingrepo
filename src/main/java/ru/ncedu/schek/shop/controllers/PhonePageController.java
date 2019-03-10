package ru.ncedu.schek.shop.controllers;


import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.ncedu.schek.shop.entities.Phone;
import ru.ncedu.schek.shop.entities.User;
import ru.ncedu.schek.shop.repos.PhoneRepository;
import ru.ncedu.schek.shop.repos.UserRepository;


@Controller
public class PhonePageController {
	@Autowired
	private PhoneRepository phones;
	@Autowired
	private UserRepository users;
	
	@RequestMapping("/phonepage")
	@GetMapping
    public String phonepage(Model model, @RequestParam(name="phoneId")long phoneId, Principal principal) {		
		Optional<Phone> pre_phone = phones.findById(phoneId);
		Phone phone = pre_phone.get();
		model.addAttribute("phone", phone);
		User user = users.findByUsername(principal.getName());
		model.addAttribute("isAdmin", user.isAdmin());
		model.addAttribute("isUser", user.isUser());
        return "phonepage";
    }
	
	@RequestMapping("/guestphonepage")
	@GetMapping
    public String guestphonepage(Model model, @RequestParam(name="phoneId")long phoneId) {		
		Optional<Phone> pre_phone = phones.findById(phoneId);
		Phone phone = pre_phone.get();
		model.addAttribute("phone", phone);
        return "guestphonepage";
    }
	
}
