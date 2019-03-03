package ru.ncedu.schek.shop.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.ncedu.schek.shop.entities.Model;
import ru.ncedu.schek.shop.repos.ModelRepository;
import ru.ncedu.schek.shop.restentities.ModelForRest;

@RestController
public class ModelRest {
	@Autowired
	ModelRepository models;
	
	@RequestMapping(value = "/models", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.ALL_VALUE )
    public ResponseEntity<List<ModelForRest>> listAllModels(){
        Iterable<Model> allModels = models.findAll();
        
        //сущности для отправки
        List<ModelForRest> modelsForRest = new ArrayList<>();
        for (Model e:allModels) {
        	ModelForRest eForRest = new ModelForRest(e);
        	modelsForRest.add(eForRest);
        }
        
        return new ResponseEntity<List<ModelForRest>>(modelsForRest, HttpStatus.OK);
    }
}
