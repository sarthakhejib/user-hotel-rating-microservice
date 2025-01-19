package com.api.user.service.services.implementation;

import com.api.user.service.entities.Hotel;
import com.api.user.service.entities.MyUser;
import com.api.user.service.entities.Rating;
import com.api.user.service.exception.ResourceNotFoundException;
import com.api.user.service.external.service.HotelService;
import com.api.user.service.repositories.UserRespository;
import com.api.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public MyUser saveUser(MyUser myUser) {
        //Generate random user id
        String userId=UUID.randomUUID().toString();
        myUser.setId(userId);
        return userRespository.save(myUser);
    }

    @Override
    public List<MyUser> getAllUsers() {
        return userRespository.findAll();
    }

    @Override
    public MyUser getUser(String id) {
        MyUser user = userRespository.findById(id).orElseThrow(()->new ResourceNotFoundException("Id is not found in the server:"+id));
       Rating[] ratingListForUser = restTemplate.getForObject("http://RATINGSERVICE/rating/user/"+id, Rating[].class);

       logger.info("{} ",ratingListForUser);

        List<Rating> ratings=  Arrays.asList(ratingListForUser).stream().map(rating -> {
           /*
           We are using 2 ways for calling an API, so that we can communicate from one microservice to other:

           1. Using RestTemplate, calling HOTELSERVICE and wrapping the response in form of ResponseEntity
           2. Using FeignClient, calling the interface method, which will give the response.
           */

            // API call to Hotel service to get Hotel name

            //(1) ResponseEntity<Hotel> hotelResponseEntity  = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            //(1) Hotel hotel= hotelResponseEntity.getBody();
            //(1) logger.info("Response Status Code:{}", hotelResponseEntity.getStatusCode());

            Hotel hotel= hotelService.getHotel(rating.getHotelId());// (2)

            //Set the User rating to the Hotel
            rating.setHotel(hotel);
            return rating;

        }).collect(Collectors.toList());

        user.setRatings(ratings);
        return user;
    }

    @Override
    public void delete(MyUser myUser) {
         userRespository.delete(myUser);
    }

    @Override
    public MyUser updateUser(String id, MyUser myUser) {
        MyUser myUser1 =null;
        if(userRespository.findById(id).isPresent()) {
            myUser1 = userRespository.findById(id).get();
            myUser1.setId(myUser.getId());
            myUser1.setName(myUser.getName());
            myUser1.setAbout(myUser.getAbout());
            myUser1.setEmail(myUser.getEmail());
        }
        return userRespository.save(myUser1);
    }
}
