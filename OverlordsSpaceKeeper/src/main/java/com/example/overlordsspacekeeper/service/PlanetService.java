package com.example.overlordsspacekeeper.service;

import com.example.overlordsspacekeeper.data.dto.request.PlanetRequest;
import com.example.overlordsspacekeeper.data.dto.response.StatusResponse;
import com.example.overlordsspacekeeper.data.dto.response.PlanetResponse;
import com.example.overlordsspacekeeper.data.entity.Lord;
import com.example.overlordsspacekeeper.data.entity.Planet;
import com.example.overlordsspacekeeper.data.repositories.LordRepository;
import com.example.overlordsspacekeeper.data.repositories.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanetService {
    private final PlanetRepository planetRepository;
    private final LordRepository lordRepository;

    public ResponseEntity<PlanetResponse> addPlanet(PlanetRequest request) {
        if(request.getName().isEmpty() || !request.getName().matches("\\w+")){
            return ResponseEntity.badRequest().body(PlanetResponse.builder().result("Invalid or empty planet`s name").build());
        }
        if(planetRepository.findByName(request.getName()).orElse(null) != null){
            return ResponseEntity.badRequest().body(PlanetResponse.builder().result("A planet with this name already exists").build());
        }

        Planet planet = new Planet();
        Lord lord = lordRepository.findById(request.getLordId()).orElse(null);

        planet.setName(request.getName());
        planet.setLord(lord);
        planetRepository.save(planet);
        if(lord != null){
            List<Planet> lordPlanets = lord.getPlanets() == null ? new ArrayList<>() : lord.getPlanets();
            lordPlanets.add(planet);
            lord.setPlanets(lordPlanets);
            lordRepository.save(lord);
        }

        return ResponseEntity.ok(PlanetResponse.builder()
                .result("ok")
                .id(planet.getId())
                .name(planet.getName())
                .lord(new PlanetResponse.Data(planet.getLord()))
                .build());
    }

    public ResponseEntity<StatusResponse> assignControl(long planetId, long lordId) {
        Planet planet = planetRepository.findById(planetId).orElse(null);
        Lord lord = lordRepository.findById(lordId).orElse(null);

        if(planet == null){
            return ResponseEntity.badRequest().body(new StatusResponse("planet is not found"));
        }
        if(planet.getLord() != null){
            return ResponseEntity.badRequest().body(new StatusResponse("The planet already has a lord"));
        }

        if(lord == null){
            return ResponseEntity.badRequest().body(new StatusResponse("lord is not found"));
        }

        planet.setLord(lord);
        planetRepository.save(planet);

        List<Planet> planets = lord.getPlanets() != null ? lord.getPlanets() : new ArrayList<>();
        planets.add(planet);
        lord.setPlanets(planets);

        return ResponseEntity.ok(new StatusResponse("ok"));
    }

    public ResponseEntity<StatusResponse> killPlanet(Long planetId) {
        Planet planet = planetRepository.findById(planetId).orElse(null);
        if(planet == null){
            return ResponseEntity.badRequest().body(new StatusResponse("planet is not found"));
        }
        planetRepository.delete(planet);
        return ResponseEntity.ok(new StatusResponse("ok"));
    }
}
