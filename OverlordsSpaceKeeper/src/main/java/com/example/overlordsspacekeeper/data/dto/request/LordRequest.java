package com.example.overlordsspacekeeper.data.dto.request;

import com.example.overlordsspacekeeper.data.entity.Planet;
import lombok.*;

import java.util.List;

@Data
@Builder
public class LordRequest {

    private String name;
    private long age;
    private Planet planet;
    private List<Planet> planetList;

}
