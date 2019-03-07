package ru.ncedu.schek.shop.service;


import ru.ncedu.schek.shop.entities.Phone;

public interface PhoneService {
	
	public void createPhone(Phone phone);
	
	public void deletePhone(long phoneId);
	
	public void updatePhone(Phone phone);
}
