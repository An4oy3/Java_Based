package com.example.overlordsspacekeeper.controller;

import com.example.overlordsspacekeeper.data.dto.request.PlanetRequest;
import com.example.overlordsspacekeeper.data.dto.response.LordResponse;
import com.example.overlordsspacekeeper.data.dto.response.PlanetResponse;
import com.example.overlordsspacekeeper.data.dto.response.StatusResponse;
import com.example.overlordsspacekeeper.data.entity.Lord;
import com.example.overlordsspacekeeper.data.entity.Planet;
import com.example.overlordsspacekeeper.service.PlanetService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PlanetController.class)
class PlanetControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PlanetService planetService;

    private static Lord lord;
    private static Planet planet;
    private static PlanetResponse planetResponse;

    @BeforeAll
    static void init(){
        lord = new Lord();
        lord.setId(1L);
        lord.setName("Test");
        lord.setAge(1L);

        planet = new Planet();
        planet.setId(1L);
        planet.setName("Test");

        planetResponse = PlanetResponse.builder().result("ok")
                .name(planet.getName())
                .id(planet.getId())
                .lord(PlanetResponse.Data.builder().id(lord.getId())
                        .name(lord.getName())
                        .age(lord.getAge())
                        .build()).build();
    }

    @Test
    void addPlanet() throws Exception {
        PlanetRequest request = new PlanetRequest("test", 1L);
       given(planetService.addPlanet(request)).willReturn(ResponseEntity.ok(planetResponse));
       mvc.perform(post("/api/v1/planet/")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"name\": \"test\", \"lordId\" : 1}")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value(planetResponse.getName()))
               .andExpect(jsonPath("$.lord").value(planetResponse.getLord()));
    }

    @Test
    void assignControl() throws Exception {
        StatusResponse statusResponse = new StatusResponse("ok");
        given(planetService.assignControl(planet.getId(), lord.getId())).willReturn(ResponseEntity.ok(statusResponse));
        mvc.perform(put("/api/v1/planet/1/assignControl")
                        .param("lordId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(statusResponse.getResult()));
    }

    @Test
    void killPlanet() throws Exception {
        StatusResponse statusResponse = new StatusResponse("ok");
        given(planetService.killPlanet(planet.getId())).willReturn(ResponseEntity.ok(statusResponse));
        mvc.perform(delete("/api/v1/planet/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(statusResponse.getResult()));
    }
}