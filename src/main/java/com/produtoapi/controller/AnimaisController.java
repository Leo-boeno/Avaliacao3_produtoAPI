package com.produtoapi.controller;

import com.produtoapi.model.Animais;
import com.produtoapi.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/animais")
public class AnimaisController {

    @Autowired
    private AnimalService animalService;

    @GetMapping
    public ResponseEntity<List<Animais>> listarTodos(){
        return ResponseEntity.ok(animalService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animais> buscarPorId(@PathVariable Long id){
        Animais animal = animalService.findById(id)
                .orElse(null);

        if (animal == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(animal);
    }

    @PostMapping
    public ResponseEntity<Animais> salvar(@Valid @RequestBody Animais animais){
        Animais salvo = animalService.salvar(animais);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animais> atualizar(@PathVariable Long id, @Valid @RequestBody Animais animais){
        Animais atualizado = animalService.atualizar(id, animais);
        return ResponseEntity.ok(atualizado);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Animais> atualizarParcialmente(@PathVariable Long id, @RequestBody Animais animaisParcial){
        Animais atualizado = animalService.atualizarParcialmente(id, animaisParcial);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        animalService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}