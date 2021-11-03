package com.example.overlordsspacekeeper.data.dto.response;

import com.example.overlordsspacekeeper.data.entity.Lord;
import com.example.overlordsspacekeeper.data.entity.Planet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanetResponse {
    private String result;
    private long id;
    private String name;
    private Data lord;

    @lombok.Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data{
        private long id;
        private String name;
        private long age;

        public Data(Lord lord){
            if(lord!=null) {
                this.id = lord.getId();
                this.name = lord.getName();
                this.age = lord.getAge();
            }
        }
    }


}
