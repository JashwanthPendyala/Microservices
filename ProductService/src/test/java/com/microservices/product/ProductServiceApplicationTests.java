//package com.microservices.product;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.math.BigDecimal;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.testcontainers.containers.MySQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.microservices.product.dto.ProductRequest;
//
//@SpringBootTest
//@Testcontainers
//@AutoConfigureMockMvc
//class ProductServiceApplicationTests {
//
//	@Container
//	static MySQLContainer<?> container = new MySQLContainer<>("mysql:5.7.34");
//
//	@Autowired
//	private MockMvc mvc;
//	
//	@Autowired
//	private ObjectMapper mapper;
//	@DynamicPropertySource
//	static void setProperties(DynamicPropertyRegistry reg) {
//		reg.add("spring.datasource.url", container::getJdbcUrl);
//		reg.add("spring.datasource.username", container::getUsername);
//		reg.add("spring.datasource.password", container::getPassword);
//	}
//
//	@Test
//	void shouldCreateProduct() throws Exception {
//		ProductRequest productRequest = getProductRequest();
//		String writeValueAsString = mapper.writeValueAsString(productRequest);
//		
//		mvc.perform(MockMvcRequestBuilders.post("/api/product").contentType(MediaType.APPLICATION_JSON).content(writeValueAsString)).andExpect(status().isCreated());
//	}
//
//	private ProductRequest getProductRequest() {
//		
//		return ProductRequest.builder().name("pqr").description("pqr").price(BigDecimal.valueOf(1200)).build();
//		
//	}
//
//}
package com.microservices.product;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.product.dto.ProductRequest;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    @Container
    static MySQLContainer<?> container = new MySQLContainer<>("mysql:5.7.34");

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry reg) {
        reg.add("spring.datasource.url", container::getJdbcUrl);
        reg.add("spring.datasource.username", container::getUsername);
        reg.add("spring.datasource.password", container::getPassword);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        ProductRequest productRequest = getProductRequest();
        String writeValueAsString = mapper.writeValueAsString(productRequest);

        mvc.perform(MockMvcRequestBuilders.post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValueAsString))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(productRequest.getName()))
                .andExpect(jsonPath("$.description").value(productRequest.getDescription()))
                .andExpect(jsonPath("$.price").value(productRequest.getPrice()));
    }

    private ProductRequest getProductRequest() {
        return ProductRequest.builder()
                .name("pqr")
                .description("pqr")
                .price(BigDecimal.valueOf(1200))
                .build();
    }
}
