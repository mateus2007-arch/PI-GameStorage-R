/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import model.Produto;

/**
 *
 * @author mateu
 */
public interface ProdutoRepository {
        List<Produto> listar();

    Produto buscarPorId(int id);

    void salvar(Produto produto);

    boolean remover(int id);
}
