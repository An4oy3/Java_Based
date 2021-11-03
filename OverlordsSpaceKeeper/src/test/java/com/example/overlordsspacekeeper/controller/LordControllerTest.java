package com.example.overlordsspacekeeper.controller;

import com.example.overlordsspacekeeper.data.dto.request.LordRequest;
import com.example.overlordsspacekeeper.data.dto.request.PlanetRequest;
import com.example.overlordsspacekeeper.data.dto.response.LordListResponse;
import com.example.overlordsspacekeeper.data.dto.response.LordResponse;
import com.example.overlordsspacekeeper.data.dto.response.PlanetResponse;
import com.example.overlordsspacekeeper.data.dto.response.StatusResponse;
import com.example.overlordsspacekeeper.data.entity.Lord;
import com.example.overlordsspacekeeper.data.entity.Planet;
import com.example.overlordsspacekeeper.data.repositories.LordRepository;
import com.example.overlordsspacekeeper.data.repositories.PlanetRepository;
import com.example.overlordsspacekeeper.service.LordService;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LordController.class)
class LordControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private LordService lordService;

    private static Lord lord;
    private static Planet planet;
    private static LordResponse lordResponse;

    @BeforeAll
    static void init(){
        lord = new Lord();
        lord.setId(1L);
        lord.setName("Test");
        lord.setAge(1L);

        planet = new Planet();
        planet.setId(1L);
        planet.setName("Test");

        lordResponse = LordResponse.builder().result("ok")
                .id(lord.getId())
                .name(lord.getName())
                .age(lord.getAge())
                .build();
    }



    @Test
    void addLord() throws Exception {
        LordResponse response = LordResponse.builder().result("ok").id(1L).name("test").age(1L).build();
        LordRequest request = LordRequest.builder().name("test").age(1).build();
        given(lordService.addLord(request)).willReturn(ResponseEntity.ok(response));
        mvc.perform(post("/api/v1/lord/")
                .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"test\", \"age\": 1}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(response.getName()))
                .andExpect(jsonPath("$.age").value(response.getAge()));
    }

    @Test
    void assignControl() throws Exception {
        StatusResponse statusResponse = new StatusResponse("ok");
        given(lordService.assignControl(lord.getId(), planet.getId())).willReturn(ResponseEntity.ok(statusResponse));
        mvc.perform(put("/api/v1/lord/1/controlAssign/")
                .param("planetId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(statusResponse.getResult()));
    }

    @Test
    void getAllParasites() throws Exception {
        List<LordResponse> lords = List.of(lordResponse);
        LordListResponse listResponse = new LordListResponse(lords.size(), lords);
        given(lordService.getAllParasites()).willReturn(ResponseEntity.ok(listResponse));
        mvc.perform(get("/api/v1/lord/parasiteLords")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.count").value(1));

    }

    @Test
    void getTopYoungestLords() throws Exception {
        List<LordResponse> lords = List.of(lordResponse);
        LordListResponse listResponse = new LordListResponse(lords.size(), lords);
        given(lordService.getAllParasites()).willReturn(ResponseEntity.ok(listResponse));
        mvc.perform(get("/api/v1/lord/parasiteLords")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.count").value(1));
    }
}