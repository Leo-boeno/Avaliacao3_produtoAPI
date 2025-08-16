package com.produtoapi.service;

import com.produtoapi.model.Produto;
import com.produtoapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos(){
        return produtoRepository.findAll();
    }

    public Produto salvar(Produto produto){

        return produtoRepository.save(produto);
    }

    public Produto atualizar(Long id, Produto produto){
        Produto existente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        existente.setNome(produto.getNome());
        existente.setQuantidade(produto.getQuantidade());
        existente.setPreco(produto.getPreco());
        existente.setStatus(produto.getStatus());
        return produtoRepository.save(existente);
    }

    public void deletar(Long id){
        produtoRepository.deleteById(id);
    }

    public Optional<Produto> findById(Long id){
        return produtoRepository.findById(id);
    }

    public Produto atualizarParcialmente(Long id, Produto produtoParcial) {
        Produto produtoExistente = findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (produtoParcial.getNome() != null) {
            produtoExistente.setNome(produtoParcial.getNome());
        }

        if (produtoParcial.getPreco() != null) {
            produtoExistente.setPreco(produtoParcial.getPreco());
        }

        // Continue com os outros campos

        return produtoRepository.save(produtoExistente);
    }

}
