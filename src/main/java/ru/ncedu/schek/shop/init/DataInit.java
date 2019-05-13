package ru.ncedu.schek.shop.init;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ru.ncedu.schek.shop.entities.Model;
import ru.ncedu.schek.shop.entities.Phone;
import ru.ncedu.schek.shop.entities.Picture;
import ru.ncedu.schek.shop.entities.Role;
import ru.ncedu.schek.shop.entities.User;
import ru.ncedu.schek.shop.repos.ModelRepository;
import ru.ncedu.schek.shop.repos.PhoneRepository;
import ru.ncedu.schek.shop.repos.PictureRepository;
import ru.ncedu.schek.shop.repos.UserRepository;
import ru.ncedu.schek.shop.utils.ImageUtil;

@Component
public class DataInit implements ApplicationRunner {
	@Autowired
	private UserRepository users;
	@Autowired
	private PhoneRepository phones;
	@Autowired
	private ModelRepository models;
	@Autowired
	private PictureRepository pictures;
	@Autowired
    private PasswordEncoder passwordEncoder;


	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		String hashedPassword = passwordEncoder.encode("admin");
		User user = new User("admin", hashedPassword);
		user.setActive(true);
		Set<Role> roles = new HashSet<Role>();
		user.setRoles(roles);
		user.addRole(Role.ADMIN);
	//	user.addRole(Role.USER);
		
		users.save(user);

		Model m0 = new Model("Generic");
		models.save(m0);
		Model m1 = new Model("Iphone");
		models.save(m1);

		Picture p0 = new Picture(m0, "generic");
		p0.setBytes(ImageUtil.loadImage("/images/iphone.jpg"));
		pictures.save(p0);
		Picture p1 = new Picture(m1, "white");
		p1.setBytes(ImageUtil.loadImage("/images/iphone_white.jpg"));
		pictures.save(p1);
		Picture p2 = new Picture(m1, "black");
		p2.setBytes(ImageUtil.loadImage("/images/iphone_black.jpg"));
		pictures.save(p2);

		// картинки сохраняются к каждому телефону непосредственно
		Phone phone1 = new Phone(m1, "white", (long) 10000);
		phone1.setPicture(p1);
		phones.save(phone1);
		Phone phone2 = new Phone(m1, "black", (long) 9900);
		phone2.setPicture(p2);
		phones.save(phone2);
		Phone phone3 = new Phone(m0);
		phone3.setPicture(p0);
		phones.save(phone3);

		Phone phone11 = new Phone(m1, "white", (long) 8000);
		phone11.setPicture(p1);
		phones.save(phone11);
		Phone phone12 = new Phone(m1, "black", (long) 8100);
		phone12.setPicture(p2);
		phones.save(phone12);
		Phone phone13 = new Phone(m0);
		phone13.setPicture(p0);
		phones.save(phone13);
		
		createLotsOfModelsForCracker(20);
	}

	public void createLotsOfModelsForCracker(int numberOfModels) throws InterruptedException {
		for (int i=0; i < numberOfModels; i++) {
			Model m = new Model("testmodel"+i);
			models.save(m);
			Picture defoltpic = new Picture(m, "defolt");
			defoltpic.setBytes(ImageUtil.loadImage("/images/no_phone.jpg"));
			pictures.save(defoltpic);
			Phone p = new Phone(m, "defolt", (long) 10000+i);
			p.setPicture(defoltpic);
			p.setComment("Со́товый телефо́н — мобильный телефон, предназначенный для\r\n" + 
					"					работы в сетях сотовой связи; использует приёмопередатчик\r\n" + 
					"					радиодиапазона и традиционную телефонную коммутацию для\r\n" + 
					"					осуществления телефонной связи на территории зоны покрытия сотовой\r\n" + 
					"					сети. В настоящее время сотовая связь — самая распространённая из\r\n" + 
					"					всех видов мобильной связи, поэтому обычно мобильным телефоном\r\n" + 
					"					называют именно сотовый телефон, хотя мобильными телефонами, помимо\r\n" + 
					"					сотовых, являются также спутниковые телефоны, радиотелефоны и\r\n" + 
					"					аппараты магистральной связи.");
			phones.save(p);
			
		}
	}
	
	
	public void searchForPictures(Phone newPhone) {
		// Model model = newPhone.getModel();
		Set<Picture> pics = newPhone.getModel().getPictures();
		for (Picture pic : pics) {
			if (newPhone.getColor() == pic.getColor()) {
				newPhone.setPicture(pic);
			}
		}

	}

	
}
