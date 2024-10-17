
package com.example.cat.controller;

import com.example.cat.api.model.Cat;
import com.example.cat.repository.CatRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CatControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CatRepository catRepository;

  private Cat cat;

  @BeforeEach
  public void setup() {
    cat = new Cat();
    cat.setId(1L);
    cat.setName("Whiskers");
    cat.setBreed("Tabby");
    cat.setAge(3);
    cat.setColor("Brown");
    cat.setDateOfBirth(new java.util.Date());
  }

  @Test
  public void testCreateCat() throws Exception {
    Mockito.when(catRepository.save(any(Cat.class))).thenReturn(cat);

    mockMvc.perform(post("/cats")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(cat)))
            .andExpect(status().isOk());
  }

  @Test
  public void testGetCats() throws Exception {
    Mockito.when(catRepository.findAll()).thenReturn(Arrays.asList(cat));

    mockMvc.perform(get("/cats"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Whiskers"));
  }

  @Test
  public void testGetCatById() throws Exception {
    Mockito.when(catRepository.findById(1L)).thenReturn(Optional.of(cat));

    mockMvc.perform(get("/cats/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Whiskers"));
  }

  @Test
  public void testUpdateCat() throws Exception {
    Mockito.when(catRepository.findById(1L)).thenReturn(Optional.of(cat));
    Mockito.when(catRepository.save(any(Cat.class))).thenReturn(cat);

    cat.setName("Mittens");

    mockMvc.perform(put("/cats/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(cat)))
            .andExpect(status().isOk());
  }

  @Test
  public void testDeleteCat() throws Exception {
    Mockito.when(catRepository.findById(1L)).thenReturn(Optional.of(cat));

    mockMvc.perform(delete("/cats/1"))
            .andExpect(status().isOk());
  }

  private static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
