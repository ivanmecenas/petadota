package br.com.mecenas.petadota.controller;

import br.com.mecenas.petadota.model.Animal;
import br.com.mecenas.petadota.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/animais")
public class AnimalController {

    @Autowired
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public List<Animal> getAllAnimais() {
        return animalService.getAllAnimais();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        return animalService.getAnimalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Animal criarAnimal(@RequestBody Animal animal) {
        return animalService.criarAnimal(animal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> atualizarAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        return animalService.atualizarAnimal(id, animal)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAnimal(@PathVariable Long id) {
        if (animalService.getAnimalById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            animalService.deletarAnimal(id);
            return ResponseEntity.noContent().build();
        }
    }
}