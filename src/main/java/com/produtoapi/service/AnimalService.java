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

    public List<Animais> listarTodos() {
        return animaisRepository.findAll();
    }

    public Animais salvar(Animais animais) {
        return animaisRepository.save(animais);
    }

    public Animais atualizar(Long id, Animais animais) {
        Animais existente = animaisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        existente.setNome(animais.getNome());
        existente.setIdade(animais.getIdade());
        existente.setAdocao(animais.getAdocao());
        existente.setChip(animais.getChip());

        return animaisRepository.save(existente);
    }

    public void deletar(Long id) {
        if (!animaisRepository.existsById(id)) {
            throw new RuntimeException("Animal não encontrado");
        }
        animaisRepository.deleteById(id);
    }

    public Optional<Animais> findById(Long id) {
        return animaisRepository.findById(id);
    }

    public Animais atualizarParcialmente(Long id, Animais parcial) {
        Animais existente = animaisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        if (parcial.getNome() != null)
            existente.setNome(parcial.getNome());

        if (parcial.getIdade() != null)
            existente.setIdade(parcial.getIdade());

        if (parcial.getAdocao() != null)
            existente.setAdocao(parcial.getAdocao());

        if (parcial.getChip() != null)
            existente.setChip(parcial.getChip());

        return animaisRepository.save(existente);
    }
}