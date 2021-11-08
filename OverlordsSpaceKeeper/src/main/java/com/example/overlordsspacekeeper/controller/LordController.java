package com.example.overlordsspacekeeper.controller;

import com.example.overlordsspacekeeper.data.dto.request.LordRequest;
import com.example.overlordsspacekeeper.data.dto.response.StatusResponse;
import com.example.overlordsspacekeeper.data.dto.response.LordResponse;
import com.example.overlordsspacekeeper.data.dto.response.LordListResponse;
import com.example.overlordsspacekeeper.service.LordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/lord")
public class LordController {

    private final LordService lordService;

    @PostMapping("/")
    public ResponseEntity<LordResponse> addLord(@RequestBody LordRequest request){
        return lordService.addLord(request);
    }

    @PutMapping("/{id}/assignControl")
    public ResponseEntity<StatusResponse> assignControl(@PathVariable Long id,
                                                        @RequestParam("planetId") Long planetId){
        return lordService.assignControl(id, planetId);
    }

    @GetMapping("/parasites")
    public ResponseEntity<LordListResponse> getAllParasites(){
        return lordService.getAllParasites();
    }

    @GetMapping("/youngest")
    public ResponseEntity<LordListResponse> getTopYoungestLords(){
        return lordService.getTopYoungestLords();
    }
}
