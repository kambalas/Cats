package com.example.cat.controller;

import com.example.cat.api.controller.CatController;
import com.example.cat.api.model.Cat;
import com.example.cat.repository.CatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class CatControllerTest {

  @Mock
  private CatRepository catRepository;

  @InjectMocks
  private CatController catController;

  private Cat cat;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    cat = new Cat();
    cat.setId(1L);
    cat.setName("Kate");
    cat.setBreed("Laukine");
    cat.setAge(3);
    cat.setColor("Juoda");
  }

  @Test
  public void testCreateCat() {
    Mockito.when(catRepository.save(any(Cat.class))).thenReturn(cat);
    catController.createCat(cat);
    Mockito.verify(catRepository).save(cat);
  }

  @Test
  public void testGetCats() {
    Pageable pageable = PageRequest.of(0, 5);
    List<Cat> cats = Arrays.asList(cat);
    Page<Cat> catPage = new PageImpl<>(cats, pageable, cats.size());
    Mockito.when(catRepository.findAll(pageable)).thenReturn(catPage);
    Page<Cat> result = catController.getCats(pageable);
    assertEquals(1, result.getContent().size());
    assertEquals("Kate", result.getContent().get(0).getName());
  }

  @Test
  public void testGetCatById() {
    Mockito.when(catRepository.findById(1L)).thenReturn(Optional.of(cat));
    Cat result = catController.getCatById(1L);
    assertNotNull(result);
    assertEquals("Kate", result.getName());
  }

  @Test
  public void testUpdateCat() {
    Mockito.when(catRepository.findById(1L)).thenReturn(Optional.of(cat));
    Mockito.when(catRepository.save(any(Cat.class))).thenReturn(cat);
    cat.setName("Kaciuks");
    catController.updateCat(1L, cat);
    Mockito.verify(catRepository).save(cat);
    assertEquals("Kacius", cat.getName());
  }

  @Test
  public void testDeleteCat() {
    Mockito.when(catRepository.findById(1L)).thenReturn(Optional.of(cat));
    catController.deleteCat(1L);
    Mockito.verify(catRepository).deleteById(1L);
  }
}
