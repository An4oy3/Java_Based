package com.example.overlordsspacekeeper.data.dto.request;

import com.example.overlordsspacekeeper.data.entity.Lord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanetRequest {
    private String name;
    private long lordId;
}
