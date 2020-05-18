package com.example;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CourseFeedback 
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String interest;
    private String managed;
    private String organized;
    private String encouraged;
    private String enthusiastic;
    private String rate;
    private String comments_instructor;
    private String comments_improve;
    private String comments_final;

    public long getId() 
    {
      return id;
    }
  
    public void setId(final long id) {
        this.id = id;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(final String name) 
    {
      this.interest = name;
    }
    /**
     * @param encouraged the encouraged to set
     */
    public void setEncouraged(String encouraged) {
        this.encouraged = encouraged;
    }
    /**
     * @return the encouraged
     */
    public String getEncouraged() {
        return encouraged;
    }
    /**
     * @param managed the managed to set
     */
    public void setManaged(String managed) {
        this.managed = managed;
    }
    /**
     * @return the managed
     */
    public String getManaged() {
        return managed;
    }
    /**
     * @param organized the organized to set
     */
    public void setOrganized(String organized) {
        this.organized = organized;
    }
    /**
     * @return the organized
     */
    public String getOrganized() {
        return organized;
    }
    /**
     * @param enthusiastic the enthusiastic to set
     */
    public void setEnthusiastic(String enthusiastic) {
        this.enthusiastic = enthusiastic;
    }/**
     * @return the enthusiastic
     */
    public String getEnthusiastic() {
        return enthusiastic;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(String rate) {
        this.rate = rate;
    }

    /**
     * @return the rate
     */
    public String getRate() {
        return rate;
    }

    /**
     * @param comments_final the comments_final to set
     */
    public void setComments_final(String comments_final) {
        this.comments_final = comments_final;
    }
    /**
     * @return the comments_final
     */
    public String getComments_final() {
        return comments_final;
    }
    /**
     * @param comments_improve the comments_improve to set
     */
    public void setComments_improve(String comments_improve) {
        this.comments_improve = comments_improve;
    }
    /**
     * @return the comments_improve
     */
    public String getComments_improve() {
        return comments_improve;
    }
    /**
     * @param comments_instructor the comments_instructor to set
     */
    public void setComments_instructor(String comments_instructor) {
        this.comments_instructor = comments_instructor;
    }
    /**
     * @return the comments_instructor
     */
    public String getComments_instructor() {
        return comments_instructor;
    }

}