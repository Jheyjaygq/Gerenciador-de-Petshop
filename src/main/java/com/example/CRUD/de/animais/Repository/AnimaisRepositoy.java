package com.example.CRUD.de.animais.Repository;

import com.example.CRUD.de.animais.Entity.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimaisRepositoy extends JpaRepository<AnimalEntity,Long> {

    List<AnimalEntity> findByEspecieIgnoreCase(String especie);
    List<AnimalEntity> findByNomeContainingIgnoreCase(String nome);
}
