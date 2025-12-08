package com.produtoapi.service;

import com.produtoapi.model.Animais;
import com.produtoapi.repository.AnimaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimaisRepository animaisRepository;

    public List<Animais> listarTodos(){
        return animaisRepository.findAll();
    }

    public Animais salvar(Animais animais){

        return animaisRepository.save(animais);
    }

    public Animais atualizar(Long id, Animais animais){
        Animais existente = animaisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        existente.setNome(animais.getNome());
        existente.setIdade(animais.getIdade());
        existente.setAdocao(animais.getAdocao());
        existente.setChip(animais.getChip());
        return animaisRepository.save(existente);
    }

    public void deletar(Long id){
        animaisRepository.deleteById(id);
    }

    public Optional<Animais> findById(Long id){
        return animaisRepository.findById(id);
    }

    public Animais atualizarParcialmente(Long id, Animais animaisParcial) {
        Animais animaisExistente = findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (animaisParcial.getNome() != null) {
            animaisExistente.setNome(animaisParcial.getNome());
        }

        if (animaisParcial.getAdocao() != null) {
            animaisExistente.setAdocao(animaisParcial.getAdocao());
        }

        // Continue com os outros campos

        return animaisRepository.save(animaisExistente);
    }

}
