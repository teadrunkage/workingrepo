package ru.ncedu.schek.shop.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.ncedu.schek.shop.entities.Model;

@Repository
public interface ModelRepository  extends CrudRepository<Model, Long>{
	List<Model> findByName(String name);
}
