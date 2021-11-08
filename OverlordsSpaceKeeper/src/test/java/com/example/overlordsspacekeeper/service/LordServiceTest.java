package com.example.overlordsspacekeeper.service;

import com.example.overlordsspacekeeper.controller.LordController;
import com.example.overlordsspacekeeper.data.dto.request.LordRequest;
import com.example.overlordsspacekeeper.data.dto.response.LordListResponse;
import com.example.overlordsspacekeeper.data.dto.response.LordResponse;
import com.example.overlordsspacekeeper.data.dto.response.StatusResponse;
import com.example.overlordsspacekeeper.data.entity.Lord;
import com.example.overlordsspacekeeper.data.entity.Planet;
import com.example.overlordsspacekeeper.data.repositories.LordRepository;
import com.example.overlordsspacekeeper.data.repositories.PlanetRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LordServiceTest {

    @Autowired
    private LordService lordService;
    @MockBean
    private LordRepository lordRepository;
    @MockBean
    private PlanetRepository planetRepository;

    @Test
    void addLordSuccessTest() {
        LordRequest request = LordRequest
                .builder()
                .name("TestName")
                .age(666)
                .build();
        Lord lord = new Lord();
        lord.setId(1L);
        lord.setName(request.getName());
        Mockito.when(lordRepository.save(any(Lord.class))).thenReturn(lord);
        ResponseEntity<LordResponse> response = lordService.addLord(request);
        assertEquals("ok", Objects.requireNonNull(response.getBody()).getResult());
        assertEquals("TestName", response.getBody().getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void addLordFailTest_InvalidName(){
        LordRequest requestNameEmpty = LordRequest.builder()
                .name("").build();
        ResponseEntity<LordResponse> responseNameEmpty = lordService.addLord(requestNameEmpty);
        assertEquals("Invalid or empty lord`s name", Objects.requireNonNull(responseNameEmpty.getBody()).getResult());
        assertEquals(HttpStatus.BAD_REQUEST, responseNameEmpty.getStatusCode());


        LordRequest requestWithNameNotMatches = LordRequest.builder()
                .name("!!").build();
        ResponseEntity<LordResponse> responseNameNotMatches = lordService.addLord(requestWithNameNotMatches);
        assertEquals("Invalid or empty lord`s name", Objects.requireNonNull(responseNameNotMatches.getBody()).getResult());
        assertEquals(HttpStatus.BAD_REQUEST, responseNameNotMatches.getStatusCode());
    }

    @Test
    void addLordFailTest_PlanetHasLord(){
        Planet planet = new Planet();
        planet.setLord(new Lord());
        Mockito.when(planetRepository.findById(Mockito.any())).thenReturn(Optional.of(planet));
        ResponseEntity<LordResponse> response = lordService.addLord(LordRequest.builder().name("Test").build());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The planet already has a lord", Objects.requireNonNull(response.getBody()).getResult());
    }

    @Test
    void assignControlFailTest() {
        Mockito.doReturn(Optional.empty())
                .when(lordRepository)
                .findById(1L);

        ResponseEntity<StatusResponse> response = lordService.assignControl(1L, 1L);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("lord is not found", Objects.requireNonNull(response.getBody()).getResult());
    }

    @Test
    void assignControlSuccessTest(){
        Lord lord = new Lord();
        lord.setId(1L);
        Planet planet = new Planet();
        planet.setId(1L);

        Mockito.when(lordRepository.findById(Mockito.any())).thenReturn(Optional.of(lord));
        Mockito.when(planetRepository.findById(Mockito.any())).thenReturn(Optional.of(planet));

        ResponseEntity<StatusResponse> response = lordService.assignControl(lord.getId(), planet.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("ok", Objects.requireNonNull(response.getBody()).getResult());
        assertEquals(planet.getLord(), lord);
    }

    @Test
    void getAllParasites() {
        Lord lordParasite = new Lord();
        lordParasite.setId(1L);

        Mockito.when(lordRepository.findAllByPlanetsEmpty()).thenReturn(List.of(lordParasite));
        ResponseEntity<LordListResponse> response = lordService.getAllParasites();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).getCount());
        assertEquals(lordParasite.getId(), response.getBody().getParasites().get(0).getId());
    }

    @Test
    void getTopYoungestLords() {
        Mockito.when(lordRepository.findTopByAge()).thenReturn(List.of(new Lord(), new Lord()));
        ResponseEntity<LordListResponse> response = lordService.getTopYoungestLords();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().getCount());
    }
}