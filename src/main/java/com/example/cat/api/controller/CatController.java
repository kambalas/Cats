package com.example.cat.api.controller;

import com.example.cat.api.model.Cat;
import com.example.cat.repository.CatRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {
  @Autowired
  private CatRepository catRepository;

  @PostMapping
  public void createCat( @Valid @RequestBody Cat cat) {
    catRepository.save(cat);
  }
  @GetMapping
  public List<Cat> getCats() {
    return catRepository.findAll();
  }
  @GetMapping("/{id}")
  public Cat getCatById(@PathVariable long id) {
    return catRepository.findById(id).orElse(null);
  }
  @PutMapping("/{id}")
  public void updateCat(@PathVariable long id, @Valid @RequestBody Cat cat) {
    cat.setId(id);
    catRepository.save(cat);
  }
  @DeleteMapping("/{id}")
  public void deleteCat(@PathVariable long id) {
    catRepository.deleteById(id);
  }

}
