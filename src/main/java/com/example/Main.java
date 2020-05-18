/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;

import static javax.measure.unit.SI.KILOGRAM;
import javax.measure.quantity.Mass;
import org.jscience.physics.model.RelativisticModel;
import org.jscience.physics.amount.Amount;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.ui.Model;

@Controller
@SpringBootApplication
public class Main 
{

  /*
  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Autowired
  private DataSource dataSource;
  */
  @Autowired
  private RatingRepository ratingRepository;

  @Autowired
  private CourseFeedbackRepository feedbackRepository;

  public static void main(String[] args) throws Exception 
  {
    SpringApplication.run(Main.class, args);
  }

  @RequestMapping("/")      // map the home of the website
  String index() 
  {
    return "index";
  }

  @GetMapping("/login")      // map the home of the website
  String login() 
  {
    return "login";
  }

  @GetMapping("/thankyou")      // map the home of the website
  String thankYou() 
  {
    return "thankyou";
  }

  @PostMapping("/login")
  String login_redirect()
  {
    return "hello";
  }

  @RequestMapping("/hello")   // map the hello page of the website
  String hello(Map<String, Object> model) 
  {
      RelativisticModel.select();
      String energy = System.getenv().get("ENERGY");
      if (energy == null) {
         energy = "12 GeV";
      }
      Amount<Mass> m = Amount.valueOf(energy).to(KILOGRAM);
      model.put("science", "E=mc^2: " + energy + " = "  + m.toString());  // print something in science variable
      return "hello";
  }

  @GetMapping("/greeting")
  public String greetingForm(Model model) 
  {
    model.addAttribute("greeting", new Greeting());
    return "greeting";
  }

  @PostMapping("/greeting")
  public String greetingSubmit(@ModelAttribute Greeting greeting) 
  {
    return "result";
  }

  @GetMapping("/rating")
  public String ratingForm(Model model) 
  {
    model.addAttribute("rating", new Rating());
    return "rating";
  }

  @PostMapping("/rating")
  public String ratingSubmit(@ModelAttribute Rating rating) 
  {
    ratingRepository.save(rating);
    return "rating_result";
  }

  @GetMapping("/coursefeedback")
  public String feedbackForm(Model model) 
  {
    model.addAttribute("feedback", new CourseFeedback());
    return "course_feedback";
  }

  @PostMapping("/coursefeedback")
  public String feedbackSubmit(@ModelAttribute CourseFeedback feedback) 
  {
    feedbackRepository.save(feedback);
    return "thankyou";
  }

  @GetMapping("/ratings")
  public String getAllRatings() 
  {
    // This returns a JSON or XML with the users
    //return ratingRepository.findAll();
    return "ratings";
  }

  @GetMapping("/all")
  public @ResponseBody Iterable<Rating> getAllUsers() 
  {
    // This returns a JSON or XML with the users
    return ratingRepository.findAll();
  }

  /*
  @GetMapping(path="/ratings")
  public @ResponseBody Iterable<Rating> getAllRatings() {
    // This returns a JSON or XML with the users
    return ratingRepository.findAll();
  }*/


/*
  @RequestMapping("/db")
  String db(Map<String, Object> model) 
  {
    try (Connection connection = dataSource.getConnection()) 
    {
      Statement stmt = connection.createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
      ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

      ArrayList<String> output = new ArrayList<String>();
      while (rs.next()) 
      {
        output.add("Read from DB: " + rs.getTimestamp("tick"));
      }

      model.put("records", output);
      return "db";
    } 
    catch (Exception e) 
    {
      model.put("message", e.getMessage());
      return "error";
    }
  }

  @Bean
  public DataSource dataSource() throws SQLException
  {
    if (dbUrl == null || dbUrl.isEmpty())               // check database url
    {
      return new HikariDataSource();
    } 
    else 
    {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
    }
  }
*/
}
