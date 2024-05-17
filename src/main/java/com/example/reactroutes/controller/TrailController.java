package com.example.reactroutes.controller;

import com.example.reactroutes.dto.TrailRegistrationRequest;
import com.example.reactroutes.model.Trail;
import com.example.reactroutes.service.TrailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/trails")
public class TrailController {

    private TrailService trailService;

    public TrailController(TrailService trailService) {
        this.trailService = trailService;
    }

    @GetMapping
    public List<Trail> getTrails() {
        return trailService.selectAllTrails();
    }

    @PostMapping
    public void addTrail(@RequestBody TrailRegistrationRequest trail) {
        trailService.insertTrail(trail);
    }
}
