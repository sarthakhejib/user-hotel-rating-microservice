package com.api.user.service.external.service;

import com.api.user.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign Client Implementation for communicating microservices together
 */

@FeignClient(name = "HOTELSERVICE")
public interface HotelService {

    @GetMapping("/hotels/{id}")
    Hotel getHotel(@PathVariable String id);

}
