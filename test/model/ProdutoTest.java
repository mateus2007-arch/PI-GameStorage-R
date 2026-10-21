/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import org.junit.Test;
import static org.junit.Assert.*;
import exception.ValidacaoException;

/**
 *
 * @author mateu
 */
public class ProdutoTest {
    
@Test
public void valorTotalEstoque(){
    Produto p = new Produto("RTX 5060", "GPU", "Nvidia", 9000, 10);
    assertEquals(90000, p.getValorEmEstoque(), 0.001);
}

@Test
public void desconto10(){
         Produto p = new Produto("RTX 5060", "GPU", "Nvidia", 9000, 10);
    assertEquals(8100, p.preComDesconto(10), 0.001);
}

@Test
public void descontoZerado(){
         Produto p = new Produto("RTX 5060", "GPU", "Nvidia", 9000, 10);
       assertEquals(9000, p.preComDesconto(0), 0.001);
}

@Test(expected = ValidacaoException.class)
public void descontoMaior100()
{
    Produto p = new Produto("RTX 5060", "GPU", "Nvidia", 9000, 10);
    p.preComDesconto(150);
}
    
    
}
