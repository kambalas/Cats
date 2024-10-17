package com.example.cat.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
@lombok.Data
public class Cat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String breed;
  private Integer age;
  private String color;
  private Date dateOfBirth;


  public Cat(String name, String breed, Integer age, String color, Date dateOfBirth) {
    this.name = name;
    this.breed = breed;
    this.age = age;
    this.color = color;
    this.dateOfBirth = dateOfBirth;
  }

  public Cat() {

  }
}
