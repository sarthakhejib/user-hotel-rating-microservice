package com.api.hotel.service.services;

import com.api.hotel.service.entity.Hotel;

import java.util.List;

public interface HotelService {

    //Save hotel in database
    Hotel createHotel(Hotel myUser);

    //Get all the hotels
    List<Hotel> getAllHotels();

    //Get a hotel from database
    Hotel getHotel(String id);

}
