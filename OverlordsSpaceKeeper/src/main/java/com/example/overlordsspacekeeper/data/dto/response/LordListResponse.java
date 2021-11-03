package com.example.overlordsspacekeeper.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LordListResponse {
    private long count;
    private List<LordResponse> parasites;
}
