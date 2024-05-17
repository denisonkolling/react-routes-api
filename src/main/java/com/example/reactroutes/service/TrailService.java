package com.example.reactroutes.service;

import com.example.reactroutes.dto.TrailRegistrationRequest;
import com.example.reactroutes.dto.TrailUpdateRequest;
import com.example.reactroutes.model.Trail;

import java.util.List;
import java.util.Optional;

public interface TrailService {

    List<Trail> selectAllTrails();
    Optional<Trail> selectTrailById(Integer trailId);
    void insertTrail(TrailRegistrationRequest trail);
    void deleteTrailById(Integer trailId);
    void updateTrail(Integer trailId, TrailUpdateRequest update);
    void updateTrail(Trail update);
}
