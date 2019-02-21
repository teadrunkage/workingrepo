package ru.ncedu.schek.shop.controllers;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import ru.ncedu.schek.shop.entities.Phone;
import ru.ncedu.schek.shop.entities.Picture;
import ru.ncedu.schek.shop.forms.PhoneForm;
import ru.ncedu.schek.shop.repos.ModelRepository;
import ru.ncedu.schek.shop.repos.PhoneRepository;
import ru.ncedu.schek.shop.repos.PictureRepository;
import ru.ncedu.schek.shop.utils.ImageUtil;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AddPhoneController {
	@Autowired
	private PhoneRepository phones;
	@Autowired
	private ModelRepository models;
	@Autowired
	private PictureRepository pictures;
	@Value("${upload.path}")
    private String uploadPath;
	
	@RequestMapping(value = { "/addphone" }, method = RequestMethod.GET)
	public String addphone(Model model) {
		PhoneForm phoneForm = new PhoneForm();
		model.addAttribute("phoneForm", phoneForm);
		return "addphone";
	}
	
	@RequestMapping(value = { "/addphone" }, method = RequestMethod.POST)
	public String savePhone(Model model, //
			@ModelAttribute("phoneForm") PhoneForm phoneForm) throws IOException, InterruptedException {

		String model_name = phoneForm.getModel_name();
		String color_name = phoneForm.getColor_name();
		
		

		ru.ncedu.schek.shop.entities.Model m;
		if (models.findByName(model_name).isEmpty()) {
			m = new ru.ncedu.schek.shop.entities.Model(model_name);
			models.save(m);
		} else {
			m = models.findByName(model_name).get(0);
		}
		Phone newPhone = new Phone(m, color_name);
		
            try {
            	Picture pic = new Picture(m, color_name);	
        		MultipartFile file = phoneForm.getPicture();
				File uploadDir = new File(uploadPath);

				if (!uploadDir.exists()) {
				    uploadDir.mkdir();
				}

      //     String uuidFile = UUID.randomUUID().toString();
     //      String resultFilename = uuidFile + "." + file.getOriginalFilename();
				String resultFilename = file.getOriginalFilename();

				file.transferTo(new File(uploadPath + "/" + resultFilename));
				System.out.println("/images/"+resultFilename);
				pic.setBytes(ImageUtil.loadImage("/images/"+resultFilename));
				pictures.save(pic);
				newPhone.setPicture(pic);
				
			} catch (Exception e) {
				Picture defoltpic = new Picture(m, color_name);
				defoltpic.setBytes(ImageUtil.loadImage("/images/no_phone.jpg"));
				pictures.save(defoltpic);
				newPhone.setPicture(defoltpic);
				e.printStackTrace();
			}


		phones.save(newPhone);

		return "redirect:/phonerepo";
	}
}
