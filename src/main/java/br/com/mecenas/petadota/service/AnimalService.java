package br.com.mecenas.petadota.service;

import br.com.mecenas.petadota.model.Animal;
import br.com.mecenas.petadota.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AnimalService {


    @Autowired
    private final AnimalRepository animalRepository;


    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }


    public Animal criarAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public Optional<Animal> atualizarAnimal(Long id, Animal animalDetails) {
        return animalRepository.findById(id).map(animal -> {
            animal.setNome(animalDetails.getNome());
            animal.setIdade(animalDetails.getIdade());
            animal.setRaca(animalDetails.getRaca());
            animal.setTipo(animalDetails.getTipo());
            animal.setDescricao(animalDetails.getDescricao());
            return animalRepository.save(animal);
        });
    }

    public List<Animal> getAllAnimais() {
        return animalRepository.findAll();
    }

    public Optional<Animal> getAnimalById(Long id) {
        return animalRepository.findById(id);
    }

    public void deletarAnimal(Long id) {
        animalRepository.deleteById(id);
    }
}