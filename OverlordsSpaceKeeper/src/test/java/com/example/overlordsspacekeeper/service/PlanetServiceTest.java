package com.example.overlordsspacekeeper.service;

import com.example.overlordsspacekeeper.data.dto.request.PlanetRequest;
import com.example.overlordsspacekeeper.data.dto.response.PlanetResponse;
import com.example.overlordsspacekeeper.data.dto.response.StatusResponse;
import com.example.overlordsspacekeeper.data.entity.Lord;
import com.example.overlordsspacekeeper.data.entity.Planet;
import com.example.overlordsspacekeeper.data.repositories.LordRepository;
import com.example.overlordsspacekeeper.data.repositories.PlanetRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PlanetServiceTest {
    @Autowired
    private PlanetService planetService;
    @MockBean
    private PlanetRepository planetRepository;
    @MockBean
    private LordRepository lordRepository;

    @Test
    void addPlanetSuccessTest() {
        PlanetRequest request = new PlanetRequest();
        request.setName("TestName");
        request.setLordId(1L);
        Lord lord = new Lord();
        lord.setId(1L);
        Mockito.when(lordRepository.findById(1L)).thenReturn(Optional.of(lord));
        Mockito.when(lordRepository.save(Mockito.any(Lord.class))).thenReturn(new Lord());
        ResponseEntity<PlanetResponse> response = planetService.addPlanet(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("ok", Objects.requireNonNull(response.getBody()).getResult());
        assertEquals("TestName", response.getBody().getName());
        assertEquals(lord.getId(), response.getBody().getLord().getId());
    }
    @Test
    void addPlanetFailedTest_EmptyName() {
        PlanetRequest request = new PlanetRequest("", 1L);
        ResponseEntity<PlanetResponse> response = planetService.addPlanet(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid or empty planet`s name", Objects.requireNonNull(response.getBody()).getResult());
    }

    @Test
    void addPlanetFailedTest_PlanetAlreadyExist() {
        PlanetRequest request = new PlanetRequest();
        request.setName("TestName");
        request.setLordId(1L);
        Mockito.when(planetRepository.findByName(Mockito.any())).thenReturn(Optional.of(new Planet()));
        ResponseEntity<PlanetResponse> response = planetService.addPlanet(request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("A planet with this name already exists", Objects.requireNonNull(response.getBody()).getResult());
    }

    @Test
    void assignControlSuccessTest() {
        Lord lord = new Lord();
        Planet planet = new Planet();
        Mockito.when(lordRepository.findById(Mockito.any())).thenReturn(Optional.of(lord));
        Mockito.when(planetRepository.findById(Mockito.any())).thenReturn(Optional.of(planet));

        ResponseEntity<StatusResponse> response = planetService.assignControl(1L, 1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("ok", Objects.requireNonNull(response.getBody()).getResult());
        assertEquals(planet.getLord(), lord);
    }

    @Test
    void assignControlFailedTest_PlanedNotFound() {
        Mockito.when(planetRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        ResponseEntity<StatusResponse> response = planetService.assignControl(1L, 1L);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("planet is not found", Objects.requireNonNull(response.getBody()).getResult());
    }

    @Test
    void assignControlFailedTest_PlanedHasLord() {
        Planet planet = new Planet();
        planet.setLord(new Lord());
        Mockito.when(planetRepository.findById(Mockito.any())).thenReturn(Optional.of(planet));
        ResponseEntity<StatusResponse> response = planetService.assignControl(1L, 1L);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The planet already has a lord", Objects.requireNonNull(response.getBody()).getResult());
    }

    @Test
    void assignControlFailedTest_LordNotFound(){
        Mockito.when(planetRepository.findById(Mockito.any())).thenReturn(Optional.of(new Planet()));
        Mockito.when(lordRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        ResponseEntity<StatusResponse> response = planetService.assignControl(1L, 1L);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("lord is not found", Objects.requireNonNull(response.getBody()).getResult());
    }


    @Test
    void killPlanetFailedTest_PlanetNotFound() {
        Mockito.when(planetRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        ResponseEntity<StatusResponse> response = planetService.killPlanet(1L);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("planet is not found", Objects.requireNonNull(response.getBody()).getResult());
    }

}