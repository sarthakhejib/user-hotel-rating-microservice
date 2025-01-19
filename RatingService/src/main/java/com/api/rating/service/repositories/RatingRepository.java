package com.api.rating.service.repositories;

import com.api.rating.service.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,String> {

    //JPA Derived methods
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}
