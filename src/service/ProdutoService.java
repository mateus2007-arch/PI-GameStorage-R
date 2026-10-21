/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import exception.ValidacaoException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;
import repository.ProdutoRepository;

/**
 *
 * @author mateu
 */
public class ProdutoService {
    
    private ProdutoRepository repositorio;
    private ProdutoValidator validador;

    public ProdutoService(ProdutoRepository repositorio, ProdutoValidator validador) {
        this.repositorio = repositorio;
        this.validador = validador;
    }

    public List<Produto> listar() {
        return repositorio.listar();
    }

    public Produto buscar(int id) {
        Produto p = repositorio.buscarPorId(id);
        if (p == null) {
            throw new ValidacaoException("Produto não encontrado.");
        }
        return p;
    }
    
    public double calcularValorTotalEstoque(){
        double total = 0;
        for(Produto p : repositorio.listar()){
            total = total + p.getValorEmEstoque();
        }
        return total;
    }

    public void cadastrar(Produto produto) {
        validador.validar(produto);
        repositorio.salvar(produto);
    }

    public void editar(int id, Produto novosDados) {
        validador.validar(novosDados);
        Produto produto = buscar(id);
        produto.setNome(novosDados.getNome());
        produto.setCategoria(novosDados.getCategoria());
        produto.setMarca(novosDados.getMarca());
        produto.setPreco(novosDados.getPreco());
        produto.setEstoque(novosDados.getEstoque());
    }

    public void excluir(int id) {
        boolean removeu = repositorio.remover(id);
        if (!removeu) {
            throw new ValidacaoException("Produto não encontrado.");
        }
    }

    public List<Produto> filtrarPorCategoria(String categoria) {
        List<Produto> resultado = new ArrayList<>();
        for (Produto p : repositorio.listar()) {
            if (p.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public List<Produto> pesquisar(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return repositorio.listar();
        }
        String busca = texto.trim().toLowerCase();
        List<Produto> resultado = new ArrayList<>();
        for (Produto p : repositorio.listar()) {
            if (p.getNome().toLowerCase().contains(busca)
                    || p.getMarca().toLowerCase().contains(busca)
                    || p.getCategoria().toLowerCase().contains(busca)) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}
