package ru.ncedu.schek.shop.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.ncedu.schek.shop.entities.Model;
import ru.ncedu.schek.shop.entities.Picture;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Long>{
	List<Picture> findByModel(Model model);
	List<Picture> findByColor(String color);
}
