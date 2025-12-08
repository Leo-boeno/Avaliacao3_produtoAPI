package com.produtoapi.controller;

import com.produtoapi.model.Animais;
import com.produtoapi.repository.AnimaisRepository;
import com.produtoapi.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produtos")
public class AnimaisController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private AnimaisRepository animaisRepository;


    @GetMapping
    public List<Animais> listarTodos(){
        return animalService.listarTodos();
    }

    @PostMapping
    public Animais salvar(@RequestBody Animais animais){
        return animalService.salvar(animais);
    }

    @PutMapping("/{id}")
    public Animais atualizar(@PathVariable Long id, @RequestBody Animais animais){
        if (!animaisRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }

        // Define o ID corretamente (caso não venha no corpo)
        animais.setId(id);

        return animaisRepository.save(animais);
    }

    @PatchMapping("/{id}")
    public Animais atualizarParcialmente(@PathVariable Long id, @RequestBody Animais animaisParcial) {
        return animalService.atualizarParcialmente(id, animaisParcial);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        animalService.deletar(id);
    }

    @GetMapping("/{id}")
    public Optional<Animais> findById(@PathVariable Long id){
        return animalService.findById(id);
    }
}
