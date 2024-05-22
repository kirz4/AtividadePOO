package com.example;

public class Produto {
    private Long id;
    private String tipo;
    private String descricao;
    private double peso;
    private int quantidade;
    private String unidadeMedida;

    public Produto() {}

    public Produto(String tipo, String descricao, double peso, int quantidade, String unidadeMedida) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.peso = peso;
        this.quantidade = quantidade;
        this.setUnidadeMedida(unidadeMedida);
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        if (unidadeMedida.equals("metro") || unidadeMedida.equals("metro quadrado") || unidadeMedida.equals("litro") || unidadeMedida.equals("kg")) {
            this.unidadeMedida = unidadeMedida;
        } else {
            throw new IllegalArgumentException("Unidade de medida inválida. Use apenas 'metro', 'metro quadrado', 'litro' ou 'kg'.");
        }
    }

    @Override
    public String toString() {
        return "Produto [id=" + id + ", tipo=" + tipo + ", descricao=" + descricao + ", peso=" + peso + " kg, quantidade=" + quantidade
                + ", unidade de medida=" + unidadeMedida + "]";
    }
}

