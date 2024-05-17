package com.example.reactroutes.service.impl;

import com.example.reactroutes.dto.TrailRegistrationRequest;
import com.example.reactroutes.dto.TrailUpdateRequest;
import com.example.reactroutes.model.Trail;
import com.example.reactroutes.repository.TrailRepository;
import com.example.reactroutes.service.TrailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrailServiceImpl implements TrailService {

    public final TrailRepository trailRepository;
    private ModelMapper mapper;

    public TrailServiceImpl(TrailRepository trailRepository, ModelMapper mapper) {
        this.trailRepository = trailRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Trail> selectAllTrails() {
        return trailRepository.findAll();
    }

    @Override
    public Optional<Trail> selectTrailById(Integer trailId) {
        return Optional.empty();
    }

    @Override
    public void insertTrail(TrailRegistrationRequest request) {
//        var trail = new Trail(request);
        var trail = new Trail();
        BeanUtils.copyProperties(request, trail);
        trailRepository.save(trail);
    }

    @Override
    public void deleteTrailById(Integer trailId) {

    }

    @Override
    public void updateTrail(Integer trailId, TrailUpdateRequest update) {

    }

    @Override
    public void updateTrail(Trail update) {

    }
}
