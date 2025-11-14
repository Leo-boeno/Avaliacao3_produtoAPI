package com.produtoapi.controller;

import com.produtoapi.model.Produto;
import com.produtoapi.repository.ProdutoRepository;
import com.produtoapi.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;


    @GetMapping
    public List<Produto> listarTodos(){
        return produtoService.listarTodos();
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto){
        return produtoService.salvar(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto){
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }

        // Define o ID corretamente (caso não venha no corpo)
        produto.setId(id);

        return produtoRepository.save(produto);
    }

    @PatchMapping("/{id}")
    public Produto atualizarParcialmente(@PathVariable Long id, @RequestBody Produto produtoParcial) {
        return produtoService.atualizarParcialmente(id, produtoParcial);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        produtoService.deletar(id);
    }

    @GetMapping("/{id}")
    public Optional<Produto> findById(@PathVariable Long id){
        return produtoService.findById(id);
    }
}
