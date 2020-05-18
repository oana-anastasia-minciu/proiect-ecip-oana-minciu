package com.example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.CourseFeedback;

@Repository
public interface CourseFeedbackRepository extends CrudRepository<CourseFeedback, Long> {
   
}