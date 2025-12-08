package com.produtoapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "tb_animais")
public class Animais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Informe um nome.")
    private String nome;

    private Integer idade;
    private Boolean adocao;
    private String chip;

    public Animais() {}

    public Animais(String nome, Integer idade, Boolean adocao, String chip) {
        this.nome = nome;
        this.idade = idade;
        this.adocao = adocao;
        this.chip = chip;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }

    public Boolean getAdocao() { return adocao; }
    public void setAdocao(Boolean adocao) { this.adocao = adocao; }

    public String getChip() { return chip; }
    public void setChip(String chip) { this.chip = chip; }