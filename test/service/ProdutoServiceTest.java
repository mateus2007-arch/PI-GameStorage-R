/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package service;

import exception.ValidacaoException;
import model.Produto;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import repository.ProdutoRepositoryMemoria;

/**
 *
 * @author mateu
 */

public class ProdutoServiceTest {
    
    private ProdutoService service;
    @Before
    public void preparar(){
    service = new ProdutoService(new ProdutoRepositoryMemoria(), new ProdutoValidator());
    service.cadastrar(new Produto("RTX 4060", "GPU", "Nvidia", 2500, 10));
    service.cadastrar(new Produto("Ryzen 5", "GPU", "AMD", 900, 8));
    }
    
    @Test
    public void valorTotalEstoque(){
       assertEquals(32200, service.calcularValorTotalEstoque(), 0.001);
    }
    
    @Test
    public void precoNegativoErro(){
        Produto p = new Produto("Mouse", "Mouse", "ALIENWARE", -5, 1);
        try{
                service.cadastrar(p);
                fail("era para dar erro");}
        catch(ValidacaoException e) {
            assertEquals(2, service.listar().size());
        }
    }
    
    @Test
    public void excluirP() {
        service.excluir(2);
        assertEquals(1, service.listar().size());
    }
    
    @Test
    public void idNaoRepeteAposExclu(){
        service.excluir(2);
        Produto novo = new Produto("Teclado", "Teclas", "ALIENWARE", 300, 15);
        service.cadastrar(novo);
        assertEquals(3, novo.getId());
    }
    

    
}
