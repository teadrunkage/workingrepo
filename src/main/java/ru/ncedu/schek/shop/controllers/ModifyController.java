package ru.ncedu.schek.shop.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.ncedu.schek.shop.entities.Phone;
import ru.ncedu.schek.shop.forms.PhoneForm;
import ru.ncedu.schek.shop.repos.ModelRepository;
import ru.ncedu.schek.shop.repos.PhoneRepository;
import ru.ncedu.schek.shop.repos.PictureRepository;
import ru.ncedu.schek.shop.service.PhoneService;


@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class ModifyController {
	
	@Autowired
	private PhoneRepository phones;
	@Autowired
	private ModelRepository models;
	@Autowired
	private PhoneService phoneService;
	
	private Phone phone;
	
	@RequestMapping(value = { "/modifyphone" }, method = RequestMethod.GET)
	public String modifyphone(Model model, @RequestParam(name="phoneId")long phoneId) {
		Optional<Phone> pre_phone = phones.findById(phoneId);
		phone = pre_phone.get();
		
		PhoneForm phoneForm = new PhoneForm();
		phoneForm.setModel_name(phone.getModelName());
		phoneForm.setColor_name(phone.getColor());
		phoneForm.setPrice(phone.getPrice());
		
		model.addAttribute("phoneForm", phoneForm); 
		return "modifyphone";
	}
	
	@RequestMapping(value = { "/modifyphone" }, method = RequestMethod.POST)
	public String savechanges(Model model, @ModelAttribute("phoneForm") PhoneForm phoneForm) {
		String model_name = phoneForm.getModel_name();
		String color_name = phoneForm.getColor_name();
		Long price = phoneForm.getPrice();
		String comment = phoneForm.getComment();
		
		ru.ncedu.schek.shop.entities.Model m;
		if (models.findByName(model_name).isEmpty()) {
			m = new ru.ncedu.schek.shop.entities.Model(model_name);
			models.save(m);
		} else {
			m = models.findByName(model_name).get(0);
		}
		
		phone.setModel(m);
		phone.setColor(color_name);
		phone.setPrice(price);
		phone.setComment(comment);
		phones.save(phone);
		//изменение на ресте
        phoneService.updatePhone(phone);
        
		return "redirect:/phonerepo";
	}

}
