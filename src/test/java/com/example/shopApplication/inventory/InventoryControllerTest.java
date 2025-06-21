package com.example.shopApplication.inventory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InventoryController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetAllProducts() throws Exception {
        Product product1 = new Product("Laptop", 10);
        product1.setId(1L);
        Product product2 = new Product("Mouse", 50);
        product2.setId(2L);

        given(inventoryService.getAllProducts()).willReturn(Arrays.asList(product1, product2));

        mockMvc.perform(get("/api/inventory/products"))
                .andExpect(status().isOk())
                .andDo(document("products-get-all",
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("[].id").description("Product ID"),
                                fieldWithPath("[].name").description("Product name"),
                                fieldWithPath("[].quantity").description("Available quantity")
                        )));
    }

    @Test
    void shouldGetProductById() throws Exception {
        Product product = new Product("Laptop", 10);
        product.setId(1L);

        given(inventoryService.getProduct(1L)).willReturn(product);

        mockMvc.perform(get("/api/inventory/products/1"))
                .andExpect(status().isOk())
                .andDo(document("products-get-by-id",
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("id").description("Product ID"),
                                fieldWithPath("name").description("Product name"),
                                fieldWithPath("quantity").description("Available quantity")
                        )));
    }

    @Test
    void shouldCreateProduct() throws Exception {
        Product createdProduct = new Product("Keyboard", 25);
        createdProduct.setId(3L);

        given(inventoryService.createProduct(anyString(), anyInt())).willReturn(createdProduct);

        InventoryController.CreateProductRequest request = new InventoryController.CreateProductRequest();
        request.setName("Keyboard");
        request.setQuantity(25);

        mockMvc.perform(post("/api/inventory/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(document("products-create",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("name").description("Product name"),
                                fieldWithPath("quantity").description("Initial quantity")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Product ID"),
                                fieldWithPath("name").description("Product name"),
                                fieldWithPath("quantity").description("Available quantity")
                        )));
    }
}
