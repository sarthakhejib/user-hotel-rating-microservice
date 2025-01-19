package com.api.rating.service.controller;

import com.api.rating.service.entities.Rating;
import com.api.rating.service.services.implementation.RatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingServiceImpl ratingService;

    /**
     * Create a Rating and insert into the Database
     * @param rating
     * @return Rating
     */
    @PostMapping()
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        Rating rating1 = ratingService.saveRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }

    /**
     * Get a rating by User ID
     * @param id
     * @return Rating
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String id) {
        List<Rating> userRatingList = ratingService.getRatingsByUserId(id);
        return ResponseEntity.ok(userRatingList);
    }

    /**
     * Get rating by Hotel ID
     * @param id
     * @return Rating
     */
    @GetMapping("/hotel/{id}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String id) {
        List<Rating> hotelRatingList = ratingService.getRatingsByHotelId(id);
        return ResponseEntity.ok(hotelRatingList);
    }

}
