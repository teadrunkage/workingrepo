package ru.ncedu.schek.shop.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ru.ncedu.schek.shop.entities.Phone;
import ru.ncedu.schek.shop.restentities.PhoneForRest;

@Service
public class PhoneServiceImpl implements PhoneService{
  //  @Autowired
  //  private PhoneRepositoryForRest phoneRepositoryForRest;

    static final String URL_PHONE_POST = "http://localhost:5030";//Cracker
    static final String URL_PHONE_UPDATE = "http://localhost:5030/phone";//Cracker
    static final String  URL_PHONE_PREFIX = "http://localhost:5030/phone";//Cracker
    
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
    public void deletePhone(long phoneId) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:5030/phones/{phoneId}";
        Object[] uriPhoneValues = new Object[] {String.valueOf(phoneId)};
        restTemplate.delete(resourceUrl,uriPhoneValues);
    }

    @Override
    public void updatePhone(Phone phone) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:5030/phone/{phoneId}";
        Object[] uriPhoneValues = new Object[]{String.valueOf(phone.getId())};
        PhoneForRest phoneForRest = new PhoneForRest(phone);
        HttpEntity<PhoneForRest> requestBody = new HttpEntity<>(phoneForRest, headers);
        //(String url, @Nullable Object request, Object... uriVariables)
        restTemplate.put(resourceUrl, requestBody, uriPhoneValues);//new Object[]{}
    }
}
