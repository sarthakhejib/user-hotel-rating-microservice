package com.api.rating.service.services;

import com.api.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {

    //Save rating in database
    Rating saveRating(Rating rating);

    //Get all ratings by user id
    List<Rating> getRatingsByUserId(String userId);

    //Get all ratings
    List<Rating> getAllRatings();

    //Get all ratings by hotel id
    List<Rating> getRatingsByHotelId(String hotelId);

}
