package com.example.overlordsspacekeeper.data.dto.request;

import com.example.overlordsspacekeeper.data.entity.Lord;
import lombok.Data;

@Data
public class PlanetRequest {
    private String name;
    private long lordId;
}
