package com.example.overlordsspacekeeper.data.dto.response;

import com.example.overlordsspacekeeper.data.entity.Lord;
import com.example.overlordsspacekeeper.data.entity.Planet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LordResponse {
    private String result;
    private long id;
    private String name;
    private long age;
    private List<Data> planets;

    @lombok.Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data{
        private long id;
        private String name;
        private long lordId;

        public Data(Planet planet){
            this.id = planet.getId();
            this.name = planet.getName();
            this.lordId = planet.getLord().getId();
        }
    }

}
