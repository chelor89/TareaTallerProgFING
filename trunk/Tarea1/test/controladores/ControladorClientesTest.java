/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import Fabrica.Fabrica;
import dataTypes.DataCliente;
import dataTypes.DataFecha;
import dataTypes.DataPedido;
import interfaces.IControladorClientes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Marcelo
 */


public class ControladorClientesTest {
     Fabrica f = Fabrica.getInstance();
     
    IControladorClientes cc = f.getIControladorClientes();
  
    String ruta = "";
    
    @BeforeClass
    public static void setUpClass() {
     
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
           cc.altaCliente("costas", "gcostas@gmail.com", "Gerardo", "Av. Italia 2078", "Costas", new DataFecha(15, 11, 1983), "francesc_font_2.jpg");
          cc.altaCliente("roro", "rcotelo@yahoo.com ", "Rodrigo", "Pdte. Berro 1548", "Cotelo", new DataFecha(2, 8, 1975),  "40-300x237.jpg");
          cc.altaCliente("chechi", "cgarrido@hotmail.com", "Cecilia", "Gral. Urquiza 1548", "Garrido", new DataFecha(12, 9, 1987),  "chechi.jpg");
          cc.altaCliente("andy", "agarcia@gmail.com", "Andrea", "Dr. Manuel Albo 4512", "García", new DataFecha(28, 07, 1951),  "98871376.jpg");
          cc.altaCliente("weiss", "aweiss@hotmail.com ", "Adrian", "Monte Caseros 5615", "Weiss", new DataFecha(23, 12, 1978), "362540d2-e952-48c3-bbea-87e4836415a1.jpg");
    }
        
    
     @After
     public void tearDown() {
     }

     /**
      * Test of actualizarIndividual method, of class ControladorRestaurantes.
      */
    /**
     * Test of altaCliente method, of class ControladorClientes.
     */
    @Test
    public void testAltaCliente() {
      int expResult = cc.listarClientes().size() + 1;
      cc.altaCliente("Sape123", "Sape123@gmail.com", "Sape", "Sape 2078", "Sape", new DataFecha(15, 11, 1983), "francesc_font_2.jpg");
      int result = cc.listarClientes().size();
      assertEquals(expResult, result);
    }
    /**
     * Test of listarClientes method, of class ControladorClientes.
     */
    @Test
    public void testListarClientes() {
       assertEquals(true, (cc.listarClientes().size() > 0));
    }

    /**
     * Test of seleccionarCliente method, of class ControladorClientes.
     */
    @Test
    public void testSeleccionarCliente() {

         cc.seleccionarCliente("costas");
         assertEquals("costas", cc.verInfoCliente().getNick());
    }

    /**
     * Test of verInfoCliente method, of class ControladorClientes.
     */
    @Test
    public void testVerInfoCliente() {
         cc.altaCliente("Sape", "Sape@gmail.com", "Sape", "Sape 2078", "Sape", new DataFecha(15, 11, 1983), "francesc_font_2.jpg");
         cc.seleccionarCliente("Sape");
         assertEquals("Sape@gmail.com", cc.verInfoCliente().getMail());
         
    }
    
}
