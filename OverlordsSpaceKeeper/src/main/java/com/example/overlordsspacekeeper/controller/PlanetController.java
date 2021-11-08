package com.example.overlordsspacekeeper.controller;

import com.example.overlordsspacekeeper.data.dto.request.PlanetRequest;
import com.example.overlordsspacekeeper.data.dto.response.StatusResponse;
import com.example.overlordsspacekeeper.data.dto.response.PlanetResponse;
import com.example.overlordsspacekeeper.service.PlanetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/planet")
public class PlanetController {

    private final PlanetService planetService;

    @PostMapping("/")
    public ResponseEntity<PlanetResponse> addPlanet(@RequestBody PlanetRequest request){
        return planetService.addPlanet(request);
    }

    @PutMapping("/{id}/assignControl")
    public ResponseEntity<StatusResponse> assignControl(@PathVariable Long id,
                                                        @RequestParam(value = "lordId", required = false) long lordId){
        return planetService.assignControl(id, lordId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StatusResponse> killPlanet(@PathVariable Long id){
        return planetService.killPlanet(id);
    }

}
