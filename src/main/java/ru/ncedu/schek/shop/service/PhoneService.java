package ru.ncedu.schek.shop.service;


import java.util.List;

import ru.ncedu.schek.shop.entities.Phone;

public interface PhoneService {
	
	public void createPhone(Phone phone);

	void deletePhone(Long phoneId);

	public void updatePhone(Phone phone);
	
	/** Return number of pages
	 * 
	 */
	public Long getNumberOfPages();
	
	/** Get list of phones for each page
	 * 
	 * @param page
	 * @return List<Phone> from that page
	 */
	public List<Phone> getPageList(Long page);
}
