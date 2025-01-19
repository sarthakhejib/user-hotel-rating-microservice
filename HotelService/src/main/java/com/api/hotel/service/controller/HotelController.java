package com.api.hotel.service.controller;

import com.api.hotel.service.entity.Hotel;
import com.api.hotel.service.services.implementation.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelServiceImpl hotelService;

    /**
     * Create Hotel and add in DB
     * @param hotel
     * @return Hotel
     */
    @PostMapping()
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel hotel1 = hotelService.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    /**
     * Get all hotels added in DB
     * @return List of Hotels
     */
    @GetMapping()
    public ResponseEntity<List<Hotel>> getAllHotel() {
        List<Hotel> myUserList = hotelService.getAllHotels();
        return ResponseEntity.ok(myUserList);
    }

    /**
     * Get a hotel by its ID
     * @param id
     * @return Hotel
     */
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String id) {
        Hotel hotel = hotelService.getHotel(id);
        return ResponseEntity.ok(hotel);
    }
}
