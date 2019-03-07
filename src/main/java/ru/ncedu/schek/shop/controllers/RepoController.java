package ru.ncedu.schek.shop.controllers;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.ncedu.schek.shop.entities.Phone;
import ru.ncedu.schek.shop.entities.User;
import ru.ncedu.schek.shop.forms.PhoneForm;
import ru.ncedu.schek.shop.repos.PhoneRepository;
import ru.ncedu.schek.shop.repos.UserRepository;
import ru.ncedu.schek.shop.service.PhoneService;

@Controller
@RequestMapping("/phonerepo")
public class RepoController {
	
	@Autowired
	private PhoneRepository phones;

	@Autowired
	private UserRepository users;
	
	@Autowired
	private PhoneService phoneService;
	
	private boolean alreadyThere = false;
	
	@GetMapping
	public String phonerepo(Model model, Principal principal) {
		Iterable<Phone> allphones = phones.findAll();
		model.addAttribute("phones", allphones);
		model.addAttribute("alreadyThere", alreadyThere);
		User user = users.findByUsername(principal.getName());
		model.addAttribute("isAdmin", user.isAdmin());
		model.addAttribute("isUser", user.isUser());
		return "phonerepo";
	}

	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/deletephone" }, method = RequestMethod.GET)
	public String deletephone(Model model, @RequestParam(name="phoneId")long phoneId, Principal principal) {
		User user = users.findByUsername(principal.getName());
		Optional<Phone> pre_phone = phones.findById(phoneId);
		Phone phone = pre_phone.get();
		for (User u : phone.getUsers()) {
			u.deletePhone(phone);
		}
		
		phones.deleteById(phoneId);
		//удаление из рест
        phoneService.deletePhone(phoneId);
        
		Iterable<Phone> allphones = phones.findAll();
		model.addAttribute("phones", allphones);
		return "redirect:/phonerepo";
	}
	
	
	@RequestMapping(value = { "/addtobasket" }, method = RequestMethod.GET)
	public String addtobasket(Model model, @RequestParam(name="phoneId")long phoneId, Principal principal) {
		User user = users.findByUsername(principal.getName());
		Optional<Phone> pre_phone = phones.findById(phoneId);
		Phone phone = pre_phone.get();
		if (user.getPhones().contains(phone)) {
			alreadyThere = true;
			return "redirect:/phonerepo";
		} else {
			alreadyThere = false;
			user.addPhone(phone);
		}
		users.save(user);
		return "redirect:/basket";
	}
	
	
	
}
