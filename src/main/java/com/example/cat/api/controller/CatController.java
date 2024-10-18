package com.example.cat.api.controller;

import com.example.cat.api.model.Cat;
import com.example.cat.api.model.exception.ResourceNotFoundException;
import com.example.cat.repository.CatRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cats")
public class CatController {

  @Autowired
  private CatRepository catRepository;

  @PostMapping
  public void createCat(@Valid @RequestBody Cat cat) {
    catRepository.save(cat);
  }

  @GetMapping
  public Page<Cat> getCats(@PageableDefault(size = 5) Pageable pageable) {
    return catRepository.findAll(pageable);
  }

  @GetMapping("/{id}")
  public Cat getCatById(@PathVariable long id) {
    return catRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cat not found with id " + id));
  }

  @PutMapping("/{id}")
  public void updateCat(@PathVariable long id, @Valid @RequestBody Cat cat) {
    if (!catRepository.existsById(id)) {
      throw new ResourceNotFoundException("Cat not found with id " + id);
    }
    cat.setId(id);
    catRepository.save(cat);
  }

  @DeleteMapping("/{id}")
  public void deleteCat(@PathVariable long id) {
    if (!catRepository.existsById(id)) {
      throw new ResourceNotFoundException("Cat not found with id " + id);
    }
    catRepository.deleteById(id);
  }
}
