package com.example.CRUD.de.animais.Service;

import com.example.CRUD.de.animais.Entity.AnimalEntity;
import com.example.CRUD.de.animais.Repository.AnimaisRepositoy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimaisRepositoy repository;

    public AnimalService(AnimaisRepositoy repository){
        this.repository = repository;
    }

    public List<AnimalEntity> buscarPorEspecie(String especie) {
        return repository.findByEspecieIgnoreCase(especie);
    }

    public AnimalEntity salvar(AnimalEntity animal) {
        if (animal.getDataCadastro() == null) {
            animal.setDataCadastro(LocalDate.now());
        }
        return repository.save(animal);
    }


    public List<AnimalEntity> listar() {
        return repository.findAll();
    }

    public Optional<AnimalEntity> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public List<AnimalEntity> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public AnimalEntity atualizar(Long id, AnimalEntity animalAtualizado) {
        Optional<AnimalEntity> animal = repository.findById(id);

        if (animal.isEmpty()) {
            System.out.println("Animal não encontrado");
            return null;
        }

        AnimalEntity animalExistente = animal.get();
        animalExistente.setNome(animalAtualizado.getNome());
        animalExistente.setEspecie(animalAtualizado.getEspecie());
        animalExistente.setIdade(animalAtualizado.getIdade());
        animalExistente.setRaca(animalAtualizado.getRaca());

        return repository.save(animalExistente);
    }


}
