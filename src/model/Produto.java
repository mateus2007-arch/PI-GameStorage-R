/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mateu
 */

public class Produto {

    private int id;
    private String nome;
    private String categoria;
    private String marca;
    private double preco;
    private int estoque;
    
    public Produto (String nome, String categoria, String marca, double preco, int estoque) {
      
            this.nome = nome;
            this.categoria = categoria;
            this.marca = marca;
            this.preco = preco;
            this.estoque = estoque;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " - " + categoria + " - " + marca + " - R$ " + preco + " - " + estoque + " un.";
    }
}
    

