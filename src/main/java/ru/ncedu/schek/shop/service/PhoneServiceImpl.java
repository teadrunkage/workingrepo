package ru.ncedu.schek.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ncedu.schek.shop.entities.Phone;
import ru.ncedu.schek.shop.repos.PhoneRepository;
import ru.ncedu.schek.shop.restentities.PhoneForRest;

import java.util.HashSet;
import java.util.Set;

@Service
public class PhoneServiceImpl implements PhoneService{

    @Autowired
    PhoneRepository phoneRepository;

    static final String URL_PHONE_POST = "http://localhost:5030";//Cracker
    static final String URL_PHONE_UPDATE = "http://localhost:5030/modifyphone";//Cracker
    static final String  URL_PHONE_PREFIX = "http://localhost:5030/phone";//Cracker
    static final String URL_PHONE_DELETE = "http://localhost:5030/deletephone";//Cracker
    
    @Override
    public void createPhone(Phone phone) {
        RestTemplate restTemplate = new RestTemplate();
        PhoneForRest phoneForRest= new PhoneForRest(phone);
        HttpEntity<PhoneForRest> requestBody = new HttpEntity<>(phoneForRest);
        try {
            PhoneForRest e = restTemplate.postForObject(URL_PHONE_POST, requestBody, PhoneForRest.class);
        } catch (Exception e) {
            System.out.println("I am falling!");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deletePhone(Long phoneId) {
        RestTemplate restTemplate = new RestTemplate();
        Phone phone= phoneRepository.findPhoneById(phoneId);
        PhoneForRest phoneForRest= new PhoneForRest(phone);
        HttpEntity<PhoneForRest> requestBody = new HttpEntity<>(phoneForRest);

        Set<String> urlSet = new HashSet<String>();
        urlSet.add(URL_PHONE_DELETE);
        for (String URL_PHONE : urlSet) {
            try {
                PhoneForRest e = restTemplate.postForObject(URL_PHONE, requestBody, PhoneForRest.class);
            } catch (Exception e) {
                System.out.println("I am falling!");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updatePhone(Phone phone) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        RestTemplate restTemplate = new RestTemplate();
        PhoneForRest phoneForRest = new PhoneForRest(phone);
        HttpEntity<PhoneForRest> requestBody = new HttpEntity<>(phoneForRest, headers);
        Set<String> urlSet = new HashSet<String>();
        urlSet.add(URL_PHONE_UPDATE);
        for (String URL_PHONE : urlSet) {
            try {
                restTemplate.put(URL_PHONE, requestBody, PhoneForRest.class);
            } catch (Exception e) {
                System.out.println("I am falling!");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
