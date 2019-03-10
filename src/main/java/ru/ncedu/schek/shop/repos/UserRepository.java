package ru.ncedu.schek.shop.repos;

import org.springframework.data.repository.CrudRepository;

import ru.ncedu.schek.shop.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);
}
