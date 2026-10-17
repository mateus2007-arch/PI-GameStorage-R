/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import exception.ValidacaoException;
import model.Produto;
import repository.ProdutoRepository;
import repository.ProdutoRepositoryMemoria;
import service.ProdutoService;
import service.ProdutoValidator;

/**
 *
 * @author mateu
 */
public class Main {
        static int ok = 0;
    static int erros = 0;

    public static void main(String[] args) {
        ProdutoRepository repositorio = new ProdutoRepositoryMemoria();
        ProdutoService service = new ProdutoService(repositorio, new ProdutoValidator());

        service.cadastrar(new Produto("RTX 4060", "GPU", "Nvidia", 2500, 10));
        service.cadastrar(new Produto("Ryzen 5", "CPU", "AMD", 900, 8));
        service.cadastrar(new Produto("Teclado Gamer", "Teclado", "Redragon", 200, 15));
        service.cadastrar(new Produto("Mouse Gamer", "Mouse", "Logitech", 150, 20));

        System.out.println("--- Testes ---");

       
        testar("lista tem 4 produtos", service.listar().size() == 4);

        service.cadastrar(new Produto("Headset", "Fone", "HyperX", 350, 5));
        testar("cadastrou o produto 5", service.listar().size() == 5);
        testar("id do novo produto é 5", service.buscar(5).getNome().equals("Headset"));

        testar("nome vazio dá erro", daErro(service, new Produto("", "GPU", "X", 10, 1)));
        testar("marca vazia dá erro", daErro(service, new Produto("Item", "GPU", "", 10, 1)));
        testar("preço negativo dá erro", daErro(service, new Produto("Item", "GPU", "X", -5, 1)));
        testar("estoque negativo dá erro", daErro(service, new Produto("Item", "GPU", "X", 10, -1)));
        testar("produto inválido não foi salvo", service.listar().size() == 5);

        service.editar(2, new Produto("Ryzen 7", "CPU", "AMD", 1500, 3));
        testar("editou o nome", service.buscar(2).getNome().equals("Ryzen 7"));
        testar("editou o preço", service.buscar(2).getPreco() == 1500);
        testar("id continua 2", service.buscar(2).getId() == 2);

        
        service.excluir(5);
        testar("excluiu o produto 5", service.listar().size() == 4);

        service.cadastrar(new Produto("Monitor", "Monitores", "LG", 900, 4));
        testar("novo id é 6 (não repete o 5)", service.listar().get(4).getId() == 6);

        boolean erro = false;
        try {
            service.excluir(99);
        } catch (ValidacaoException e) {
            erro = true;
        }
        testar("excluir id que não existe dá erro", erro);

        testar("filtro por GPU acha 1", service.filtrarPorCategoria("GPU").size() == 1);
        testar("pesquisa 'logitech' acha 1", service.pesquisar("logitech").size() == 1);
        testar("pesquisa vazia traz todos", service.pesquisar("").size() == service.listar().size());
        testar("pesquisa com '(' não quebra", service.pesquisar("(").size() == 0);

        System.out.println();
        System.out.println("Lista final:");
        for (Produto p : service.listar()) {
            System.out.println(p);
        }
        System.out.println();
        System.out.println("Certos: " + ok + " | Errados: " + erros);
    }

    static boolean daErro(ProdutoService service, Produto p) {
        try {
            service.cadastrar(p);
            return false;
        } catch (ValidacaoException e) {
            return true;
        }
    }

    static void testar(String descricao, boolean resultado) {
        if (resultado) {
            ok++;
            System.out.println("OK    - " + descricao);
        } else {
            erros++;
            System.out.println("ERRO  - " + descricao);
        }
    }
}
