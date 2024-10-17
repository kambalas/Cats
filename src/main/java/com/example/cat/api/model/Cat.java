package com.example.cat.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.util.Date;

@Entity
@lombok.Data
public class Cat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Name is mandatory")
  private String name;
  @NotBlank(message = "Breed is mandatory")
  private String breed;
  @NotNull(message = "Age is mandatory")
  @Min(value = 0, message = "Age must be zero or positive")
  private Integer age;
  @NotBlank(message = "Color is mandatory")
  private String color;
  @NotNull(message = "Date of birth is mandatory")
  @Past(message = "Date of birth must be in the past")
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
