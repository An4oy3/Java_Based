package com.example.productlist.service;

import com.example.productlist.exception.EntityNotFoundException;
import com.example.productlist.model.dto.request.ProductRequest;
import com.example.productlist.model.dto.response.ProductResponse;
import com.example.productlist.model.entity.List;
import com.example.productlist.model.entity.Product;
import com.example.productlist.model.repository.ListRepository;
import com.example.productlist.model.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private ListRepository listRepository;

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
    void getProduct_FailTest_IncorrectARG() {
        Mockito.when(productRepository.count()).thenReturn(1L);
        Throwable ex = catchThrowable(()->  productService.getProduct(2L));

        assertThat(ex).isInstanceOf(EntityNotFoundException.class);
        assertEquals("Invalid Product Id", ex.getMessage());
    }

    @Test
    void getProductSuccessTest() {
        Mockito.when(productRepository.count()).thenReturn(1L);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(productRepository.count()).thenReturn(1L);
        ResponseEntity<ProductResponse> response = productService.getProduct(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product.getId(), response.getBody().getId());
        assertEquals(product.getName(), response.getBody().getName());
        assertEquals("ok", response.getBody().getError());
    }

    @Test
    void addProduct() {
        Mockito.when(listRepository.findByName(Mockito.anyString())).thenReturn(list);
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        ResponseEntity<ProductResponse> response = productService.addProduct(ProductRequest.builder().name("Test").description("TestDescription").kcal(10).listName("Test").build());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        assertEquals("ok", response.getBody().getError());
        assertEquals("Test", response.getBody().getName());
    }

    @Test
    void changeProductList_FailTest_IncorrectARG() {
        Mockito.when(productRepository.count()).thenReturn(1L);
        Throwable ex = catchThrowable(()->  productService.changeProductList(2L, "Test"));

        assertThat(ex).isInstanceOf(EntityNotFoundException.class);
        assertEquals("Invalid Product Id", ex.getMessage());
    }

    @Test
    void changeProductList_FailTest_InvalidListName(){
        Mockito.when(productRepository.count()).thenReturn(1L);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(listRepository.findByName(Mockito.anyString())).thenReturn(null);

        Throwable ex = catchThrowable(()-> productService.changeProductList(1L, "Fail"));

        assertThat(ex).isInstanceOf(EntityNotFoundException.class);
        assertEquals("Invalid List Name", ex.getMessage());
    }

    @Test
    void changeProductListSuccessTest(){
        Mockito.when(productRepository.count()).thenReturn(1L);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(listRepository.findByName(Mockito.anyString())).thenReturn(list);
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);

        ResponseEntity<ProductResponse> response = productService.changeProductList(1L, "test");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Test", response.getBody().getName());
    }

}