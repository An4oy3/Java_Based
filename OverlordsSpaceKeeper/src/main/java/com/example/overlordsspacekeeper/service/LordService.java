package com.example.overlordsspacekeeper.service;

import com.example.overlordsspacekeeper.data.dto.request.LordRequest;
import com.example.overlordsspacekeeper.data.dto.response.StatusResponse;
import com.example.overlordsspacekeeper.data.dto.response.LordResponse;
import com.example.overlordsspacekeeper.data.dto.response.LordListResponse;
import com.example.overlordsspacekeeper.data.entity.Lord;
import com.example.overlordsspacekeeper.data.entity.Planet;
import com.example.overlordsspacekeeper.data.repositories.LordRepository;
import com.example.overlordsspacekeeper.data.repositories.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LordService {

    private final LordRepository lordRepository;
    private final PlanetRepository planetRepository;

    public ResponseEntity<LordResponse> addLord(LordRequest request){
        if(request.getName().isEmpty() || !request.getName().matches("\\w+")){
            return ResponseEntity.badRequest().body(LordResponse.builder().result("Invalid or empty lord`s name").build());
        }
        Planet planet = planetRepository.findById(request.getPlanetId()).orElse(null);

        if(planet != null && planet.getLord() != null){
            return ResponseEntity.badRequest().body(LordResponse.builder().result("The planet already has a lord").build());
        }

        Lord lord = new Lord();
        lord.setName(request.getName());
        lord.setAge(request.getAge());
        lord.setPlanets(planet == null ? new ArrayList<>() : List.of(planet));
        lord = lordRepository.save(lord);
        if(planet != null) {
           planet.setLord(lord);
           planetRepository.save(planet);
        }
        return ResponseEntity.ok(createLordResponseList(lord));
    }


    public ResponseEntity<StatusResponse> assignControl(Long lordId, long planetId){
        Lord lord = lordRepository.findById(lordId).orElse(null);
        Planet planet = planetRepository.findById(planetId).orElse(null);
        if(lord == null){
            return ResponseEntity.badRequest().body(new StatusResponse("lord is not found"));
        }
        if(planet == null){
            return ResponseEntity.badRequest().body(new StatusResponse("planet is not found"));
        }
        if(planet.getLord() != null){
            return ResponseEntity.badRequest().body(new StatusResponse("The planet already has a lord"));
        }

        planet.setLord(lord);

        List<Planet> planets = lord.getPlanets() != null ? lord.getPlanets() : new ArrayList<>();
        planets.add(planet);
        lord.setPlanets(planets);
        planetRepository.save(planet);
        return ResponseEntity.ok(new StatusResponse("ok"));
    }

    public ResponseEntity<LordListResponse> getAllParasites(){
        List<LordResponse> lordResponseList = lordRepository.findAllByPlanetsEmpty().stream()
                .map(this::createLordResponseList)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new LordListResponse(lordResponseList.size(), lordResponseList));
    }

    public ResponseEntity<LordListResponse> getTopYoungestLords(){
        List<LordResponse> lordResponseList = lordRepository.findTopByAge().stream()
                .map(this::createLordResponseList)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new LordListResponse(lordResponseList.size(), lordResponseList));
    }

    private LordResponse createLordResponseList(Lord lord){
        return LordResponse
                .builder()
                .result("ok")
                .id(lord.getId())
                .name(lord.getName())
                .age(lord.getAge())
                .planets(lord.getPlanets() == null ? null : lord.getPlanets().stream().map(p ->
                        LordResponse.Data
                                .builder()
                                .id(p.getId())
                                .name(p.getName())
                                .lordId(p.getLord().getId()).build()).collect(Collectors.toList()))
                .build();
    }
}
