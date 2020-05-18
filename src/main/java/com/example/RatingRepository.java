package com.example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Rating;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {
    
}



