package com.example.reactroutes.repository;

import com.example.reactroutes.model.Trail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrailRepository extends JpaRepository<Trail, Integer> {
}
