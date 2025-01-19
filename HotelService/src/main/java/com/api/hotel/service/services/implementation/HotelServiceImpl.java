package com.api.hotel.service.services.implementation;

import com.api.hotel.service.entity.Hotel;
import com.api.hotel.service.exceptions.ResourceNotFoundException;
import com.api.hotel.service.repositories.HotelRepository;
import com.api.hotel.service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String hotelId= UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String id) {
        Hotel hotel= hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Id is not found in the server:"+id));
        return hotel;
    }
}
