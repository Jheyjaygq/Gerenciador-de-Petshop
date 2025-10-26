package com.example.CRUD.de.animais.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "animais")
public class AnimalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=100)
    private String especie;

    @Column(length=100)
    private String raca;

    @Column(nullable=false)
    private int idade;

    @Column(name="data_cadastro")
    private LocalDate dataCadastro;

    @Column(length=100)
    private String nome;
}

