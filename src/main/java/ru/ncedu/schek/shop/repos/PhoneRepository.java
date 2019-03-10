package ru.ncedu.schek.shop.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.ncedu.schek.shop.entities.Phone;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, Long>{
    @Query(value="SELECT * FROM Phones WHERE Phones.PHONE_ID like %?1%",nativeQuery = true)
    Phone findPhoneById(Long phone);
}
