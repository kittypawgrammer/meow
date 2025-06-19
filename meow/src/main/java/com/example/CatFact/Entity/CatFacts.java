package com.example.CatFact.Entity;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
public class CatFacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @NotBlank(message = "Text cannot be blank")
    @Column(unique= true)
    private String text;
    private Time createdTime;
}
