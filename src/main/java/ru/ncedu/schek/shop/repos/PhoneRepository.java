package ru.ncedu.schek.shop.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.ncedu.schek.shop.entities.Phone;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, Long>{
	
}
