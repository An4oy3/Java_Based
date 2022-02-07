package com.example.productlist.service;

import com.example.productlist.exception.EntityAlreadyExists;
import com.example.productlist.exception.EntityNotFoundException;
import com.example.productlist.model.dto.request.ListRequest;
import com.example.productlist.model.dto.response.ListResponse;
import com.example.productlist.model.entity.List;
import com.example.productlist.model.entity.Product;
import com.example.productlist.model.repository.ListRepository;
import com.example.productlist.model.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ListServiceTest {

    @Autowired
    private ListService listService;

    @MockBean
    private ListRepository listRepository;
    @MockBean
    private ProductRepository productRepository;

    private static Product product;
    private static List list;

    @BeforeAll
    static void init(){
        product = new Product();
        product.setId(1L);
        product.setName("Test");
        product.setDescription("TestDescription");
        product.setKcal(10);
        list = new List();
        list.setId(1L);
        list.setName("Test");
        list.setProducts(java.util.List.of(product));
        product.setList(list);
    }

    @Test
    void getList_FailTest_IncorrectARG() {
        Mockito.when(listRepository.count()).thenReturn(1L);

        Throwable ex = catchThrowable(()-> listService.getList(2L));

        assertThat(ex).isInstanceOf(EntityNotFoundException.class);
        assertEquals("Invalid List Id", ex.getMessage());

    }

    @Test
    void getListSuccessTest(){
        Mockito.when(listRepository.count()).thenReturn(1L);
        Mockito.when(listRepository.findById(1L)).thenReturn(Optional.of(list));

        ResponseEntity<ListResponse> response = listService.getList(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list.getId(), response.getBody().getId());
        assertEquals(list.getName(), response.getBody().getName());
        assertEquals("ok", response.getBody().getError());
        assertEquals(10L, response.getBody().getSumKcal());
    }

    @Test
    void addList_FailTest_IncorrectARG() {
        Mockito.when(listRepository.findByName(Mockito.anyString())).thenReturn(list);

        ListRequest request = new ListRequest("Fail", java.util.List.of(1L));
        Throwable ex = catchThrowable(()-> listService.addList(request));

        assertThat(ex).isInstanceOf(EntityAlreadyExists.class);
        assertEquals("List already exists", ex.getMessage());
    }

    @Test
    void addListSuccessTest(){
        Mockito.when(listRepository.findByName(Mockito.anyString())).thenReturn(null);
        Mockito.when(productRepository.findAllById(java.util.List.of(1L))).thenReturn(java.util.List.of(product));
        Mockito.when(listRepository.save(Mockito.any())).thenReturn(list);

        ListRequest request = new ListRequest("Test", java.util.List.of(1L));
        ResponseEntity<ListResponse> response = listService.addList(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("ok", response.getBody().getError());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Test", response.getBody().getName());
    }
}