package com.example.reactroutes.model;

import com.example.reactroutes.dto.TrailRegistrationRequest;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tab_trails")
public class Trail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String location;
    private Double rate;
    private Integer reviews;
    private Double length;
    private String time;
    private String imageURL;

    public Trail() {
    }

    public Trail(Integer id, String title, String location, Double rate, Integer reviews, Double length, String time, String imageURL) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.rate = rate;
        this.reviews = reviews;
        this.length = length;
        this.time = time;
        this.imageURL = imageURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getReviews() {
        return reviews;
    }

    public void setReviews(Integer reviews) {
        this.reviews = reviews;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trail trail = (Trail) o;
        return Objects.equals(id, trail.id) && Objects.equals(title, trail.title) && Objects.equals(location, trail.location) && Objects.equals(rate, trail.rate) && Objects.equals(reviews, trail.reviews) && Objects.equals(length, trail.length) && Objects.equals(time, trail.time) && Objects.equals(imageURL, trail.imageURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, location, rate, reviews, length, time, imageURL);
    }
}
