package ru.ncedu.schek.shop.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.ncedu.schek.shop.entities.Phone;
import ru.ncedu.schek.shop.entities.User;
import ru.ncedu.schek.shop.repos.PhoneRepository;
import ru.ncedu.schek.shop.repos.UserRepository;
import ru.ncedu.schek.shop.service.PhoneService;



@Controller
public class BulkDeleteController {
	@Autowired
	private PhoneRepository phones;
	@Autowired
	private PhoneService phoneService;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/bulkDelete", method=RequestMethod.POST)
	public String bulkDelete(Model model, @RequestParam("idChecked") List<Long> ids) {
		for (Long id:ids) {
			Optional<Phone> pre_phone = phones.findById(id);
			Phone phone = pre_phone.get();
			for (User u : phone.getUsers()) {
				u.deletePhone(phone);
			}
			
			phones.deleteById(id);
			//удаление из рест
	        phoneService.deletePhone(id);
		}
		return "redirect:/phonerepo";
	}
}
