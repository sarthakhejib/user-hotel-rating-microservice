package com.api.user.service.controller;

import com.api.user.service.entities.MyUser;
import com.api.user.service.services.implementation.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * Create a User and save in Database
     * @param myUser
     * @return MyUser
     */
    @PostMapping()
    public ResponseEntity<MyUser> createUser(@RequestBody MyUser myUser) {
        MyUser myUser1 = userService.saveUser(myUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(myUser1);
    }

    /**
     * Get all Users
     * @return List of MyUsers
     */
    @GetMapping()
    public ResponseEntity<List<MyUser>> getAllUsers() {
        List<MyUser> myUserList = userService.getAllUsers();
        return ResponseEntity.ok(myUserList);
    }

    /**
     * Below patterns have been implemented:
     * 1. Circuit beaker is enabled here with Fallback.
     * 2. Retry means it will try for ((N) Number of times and wait for some time)
     * if the service is not responding it will then call the fallback method
     * 3. Rate Limiter will limit the number of request the endpoint is getting
     * Once the threshold is hit it calls the fallback
     *
     * NOTE: Comment/Uncomment the annotations below to test the behaviours
     */

    /**
     * Fetch a user on the basis of ID
     * @param id
     * @return MyUser
     */
    @GetMapping("/{id}")
    //@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    //@CircuitBreaker(name = "ratingHotelCircuitBreaker", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<MyUser> getUser(@PathVariable String id) {
        MyUser myUser1 = userService.getUser(id);
        return ResponseEntity.ok(myUser1);
    }

    /**
     * FALLBACK method. A dummy response is returned as either RatingService/HotelService is down
     * @param id
     * @param exception
     * @return Dummy MyUser
     */
    public ResponseEntity<MyUser> ratingHotelFallback(String id, Exception exception) {
        logger.info("Fallback service is called as the requested servic is down:", exception.getMessage());
        MyUser user= new MyUser("123","DummyName","dummy@gmail.com","Is this called as the requested service is down",null);
        return ResponseEntity.ok(user);
    }

}
