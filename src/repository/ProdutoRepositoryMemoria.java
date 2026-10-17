/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import model.Produto;

/**
 *
 * @author mateu
 */

public class ProdutoRepositoryMemoria implements ProdutoRepository{
    
    private List<Produto> lista = new ArrayList<>();
    private int proximoId = 1;

    
    @Override
    public List<Produto> listar() {
        return lista;
    }

    @Override
    public Produto buscarPorId(int id) {
        for (Produto p : lista) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void salvar(Produto produto) {
        
        if (produto.getId() == 0) {
            produto.setId(proximoId);
            proximoId++;
            lista.add(produto);
        }
    }

    @Override
    public boolean remover(int id) {
        Produto p = buscarPorId(id);
        if (p == null) {
            return false;
        }
        lista.remove(p);
        return true;
    }
}
