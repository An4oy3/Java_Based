package com.example.productlist.controller;

import com.example.productlist.model.dto.request.ListRequest;
import com.example.productlist.model.dto.response.ListResponse;
import com.example.productlist.model.entity.List;
import com.example.productlist.service.ListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lists/")
@Api("Работа с списками продуктов")
public class ListController {
    private final ListService listService;

    @GetMapping("")
    @ApiOperation("Получение списка продуктов")
    public ResponseEntity<ListResponse> getList(@RequestParam Long id){
        return listService.getList(id);
    }

    @PostMapping("")
    @ApiOperation("Добавление нового списка продуктов")
    public ResponseEntity<ListResponse> addList(@RequestBody ListRequest request){
        return listService.addList(request);
    }

}
