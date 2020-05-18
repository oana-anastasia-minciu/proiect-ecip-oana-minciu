package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/")
public class RatingController 
{
    private final RatingRepository repository;
    public RatingController(RatingRepository repository)
    {
        this.repository = repository;
    }
    
  @GetMapping("/all")
  public List<Rating> getAllUsers()
  {
    // This returns a JSON or XML with the users
    System.out.println(((List<Rating>)repository.findAll()).size());
    return (List<Rating>)repository.findAll();
  }
  
}