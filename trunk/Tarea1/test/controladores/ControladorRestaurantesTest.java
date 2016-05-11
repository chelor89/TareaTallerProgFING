/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Fabrica.Fabrica;
import dataTypes.DataPedido;
import dataTypes.DataProducto;
import dataTypes.DataRestaurante;
import interfaces.IControladorRestaurantes;
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
public class ControladorRestaurantesTest {
     Fabrica f = Fabrica.getInstance();
     
     IControladorRestaurantes cr = f.getIControladorRestaurantes();

     public ControladorRestaurantesTest() {
     }

     @BeforeClass
     public static void setUpClass() {

     }

     @AfterClass
     public static void tearDownClass() {
     }

     @Before
     public void setUp() {
          cr = new ControladorRestaurantes();
          cr.altaCategoria("Chivitos");
          cr.altaCategoria("Minutas");
          cr.altaCategoria("Parilla");
          cr.altaCategoria("Pizzas");
          cr.altaCategoria("Empanadas");
          cr.altaCategoria("Milanesas");
          cr.altaCategoria("Ensaladas");
          cr.altaCategoria("Pastas");
          cr.altaCategoria("Comida China");
          cr.altaCategoria("Picadas");
          cr.altaCategoria("Woks");
          cr.altaCategoria("Comida Mexicana");
          cr.altaCategoria("Entradas");
          cr.altaCategoria("Bebidas");
          cr.altaCategoria("Sushi");

          cr.altaRestaurante("bocatti", "bocatti@gmail.com", "Empanadas Bocatti", "18 de julio 2138", new HashSet<String>());
          cr.altaRestaurante("winb", "wok.in.box@hotmail.com", "Wok in Box", "Libertad 2535", new HashSet<String>());
          cr.altaRestaurante("mera", "mera@hotmail.com", "Pizzeria Mera", "Av 8 de octubre 2704", (new HashSet<String>()));
          cr.altaRestaurante("rossell", "bar.rossel@gmail.com", "Bar Rossell", "Bvar. Artigas 1601", new HashSet<String>());

          cr.seleccionarRestaurante("mera");
          cr.seleccionarCategoria("Chivitos");
          cr.agregarCategoriasARestaurante();
          cr.seleccionarCategoria("Minutas");
          cr.agregarCategoriasARestaurante();
          cr.seleccionarCategoria("Parilla");
          cr.agregarCategoriasARestaurante();
          cr.seleccionarCategoria("Pizzas");
          cr.agregarCategoriasARestaurante();

          cr.seleccionarRestaurante("rossell");
          cr.seleccionarCategoria("Chivitos");
          cr.agregarCategoriasARestaurante();
          cr.seleccionarCategoria("Minutas");
          cr.agregarCategoriasARestaurante();
          cr.seleccionarCategoria("Parilla");
          cr.agregarCategoriasARestaurante();
          cr.seleccionarCategoria("Pastas");
          cr.agregarCategoriasARestaurante();

          cr.seleccionarRestaurante("bocatti");
          cr.seleccionarCategoria("Empanadas");
          cr.agregarCategoriasARestaurante();

          cr.seleccionarRestaurante("winb");
          cr.seleccionarCategoria("Pastas");
          cr.agregarCategoriasARestaurante();
          cr.seleccionarCategoria("Comida China");
          cr.agregarCategoriasARestaurante();
          cr.seleccionarCategoria("Woks");
          cr.agregarCategoriasARestaurante();

          cr.altaIndividual("mera", "Asado", "Asado a la parrilla", 225, "");
          cr.altaIndividual("mera", "Milanesa de Carne", "Con lechuga, tomate, mayonesa y fritas", 180, "milanesa-con-fritas.jpg");
          cr.altaIndividual("mera", "Chivito canadiense", "Lomito, jamón, muzza, tomate, aceitunas, panceta, huevo, morrón y fritas", 305, "chivito.jpg");
          cr.altaIndividual("mera", "Pizza 2 gustos", "Pizza con dos gustos a elección", 130, "");
          cr.altaIndividual("rossell", "Chivito al plato", "Ensalada rusa, mixta, huevo, jamón, muzza, panceta, aceitunas y fritas", 324, "");
          cr.altaIndividual("rossell", "Milanesa a caballo", "Milanesa con dos huevos fritos acompañado de fritas", 270, "215751-sabores450-thumb.jpg");
          cr.altaIndividual("rossell", "Pizza con 2 gustos", "Pizza con dos gustos a elección", 103, "");
          cr.altaIndividual("rossell", "Agnolotis", "Agnolotis de jamón y queso", 225, "sorrentinos-caprese.jpg");
          cr.altaIndividual("bocatti", "Empanada de Carne", "Carne, aceitunas, huevo duro, condimentos", 44, "empanada_argentina.jpg");
          cr.altaIndividual("bocatti", "Empanada Americana", "Carne, panceta y huevo duro", 44, "");
          cr.altaIndividual("bocatti", "Empanada QyC", "Empanada de Queso y Cebolla", 44, "cebollayqueso.jpg");
          cr.altaIndividual("bocatti", "Empanada Capresse", "Queso, tomate y albahaca", 44, "Caprese.jpg");
          cr.altaIndividual("winb", "Thai wok", "Cerdo, calamares, sweet chili, salsa de ostras, maní y jugo de lima, acompañado de tallarines o arroz.", 240, "wok-verduras-thai-contoque-paprika-300x171.jpg");
          cr.altaIndividual("winb", "China wok", "Tempura de cerdo, vegetales mixtos, almendras, salsa de soja y naranja, acompañado de tallarines o arroz.", 240, "");
          cr.altaIndividual("winb", "Classic wok de pollo", "Pollo, vegetales mixtos, salsa agridulce, salsa de soja y cebollita de verdeo, acompañado de tallarines o arroz.", 230, "images.jpg");
          cr.altaIndividual("winb", "Classic wok de cerdo", "Cerdo, vegetales mixtos, jengibre, salsa de ostras y ralladura de lima, acompañado de tallarines o arroz.", 230, "");

          cr.seleccionarRestaurante("mera");

          cr.altaPromocion("mera", "ChiviPizza", "Chivito y Pizza", 20, 666666, "");
          cr.seleccionarProductoDeRestaurante("ChiviPizza");
          cr.agregarIndividualAPromocion("Chivito canadiense", 1);
          cr.agregarIndividualAPromocion("Pizza 2 gustos", 1);

          cr.altaPromocion("mera", "MilaAsado", "3 Milanesas + 1 Asado para compartir", 30, 666666, "");
          cr.seleccionarProductoDeRestaurante("MilaAsado");
          cr.agregarIndividualAPromocion("Milanesa de Carne", 3);
          cr.agregarIndividualAPromocion("Asado", 1);

          cr.seleccionarRestaurante("rossell");
          cr.altaPromocion("rossell", "MilaPizza", "2 Milanesas a caballo + 1 Pizzas 2 gustos", 10, 666666, "");
          cr.seleccionarProductoDeRestaurante("MilaPizza");
          cr.agregarIndividualAPromocion("Milanesa a caballo", 2);
          cr.agregarIndividualAPromocion("Pizza con 2 gustos", 1);
     }

     @After
     public void tearDown() {
     }

     /**
      * Test of actualizarIndividual method, of class ControladorRestaurantes.
      */
     @Test
     public void testActualizarIndividual() {

          String nombre = "Asado2";
          String descripcion = "Asado a la parrilla";
          String imagen = "";
          int precio = 225;

          cr.seleccionarProducto("Asado", "mera");
          cr.actualizarIndividual(nombre, descripcion, imagen, precio);
          String expResult = "Asado2";
          String result = cr.verInfoProducto().getNombre();

          assertEquals(expResult, result);
          System.out.println("actualizarIndividual");

          // TODO review the generated test code and remove the default call to fail.
          //fail("The test case is a prototype.");
     }

     /**
      * Test of actualizarPromocion method, of class ControladorRestaurantes.
      */
     @Test
     public void testActualizarPromocion() {
          System.out.println("actualizarPromocion");
          String nombre = "ChiviPizza";
          String descripcion = "Chivito y Pizzazzzz";
          String imagen = "";
          Boolean activar = true;
          int descuento = 20;

          cr.seleccionarProducto("ChiviPizza", "mera");
          cr.actualizarPromocion(nombre, descripcion, imagen, activar, descuento);

          String expResult = "Chivito y Pizzazzzz";
          String result = cr.verInfoProducto().getDescripcion();

          assertEquals(expResult, result);
     }

     @Test
     public void testAltaCategoria() {

          int expResult = cr.listarCategorias().size() + 1;
          System.out.println("altaCategoria");
          cr.altaCategoria("Pijas");

          int result = cr.listarCategorias().size();
          assertEquals(expResult, result);
          // TODO review the generated test code and remove the default call to fail.
          //fail("The test case is a prototype.");
     }

     @Test
     public void testAgregarCategoriasARestaurante() {
          cr.seleccionarCategoria("Woks");
          cr.seleccionarRestaurante("mera");
          int expResult = cr.listarRestaurantesCategoria().size() + 1;

          cr.agregarCategoriasARestaurante();

          int result = cr.listarRestaurantesCategoria().size();
          System.out.println("agregarCategoriasARestaurante");

          assertEquals(expResult, result);
          // TODO review the generated test code and remove the default call to fail.
          // fail("The test case is a prototype.");
     }

     /**
      * Test of agregarIndividualAPromocion method, of class
      * ControladorRestaurantes.
      */
     @Test
     public void testAgregarIndividualAPromocion() {
          cr.seleccionarRestaurante("rossell");

          int expResult = cr.verInfoRestauranteM().getProductos().size() + 1;

          cr.altaPromocion("rossell", "MilaPizza2", "2 Milanesas a caballo + 1 Pizzas 2 gustos", 10, 0, "");
          cr.seleccionarProductoDeRestaurante("MilaPizza2");
          cr.agregarIndividualAPromocion("Milanesa a caballo", 2);
          cr.agregarIndividualAPromocion("Pizza con 2 gustos", 1);

          int result = cr.verInfoRestauranteM().getProductos().size();

          System.out.println("agregarIndividualAPromocion");

          assertEquals(expResult, result);
          // TODO review the generated test code and remove the default call to fail.
          //fail("The test case is a prototype.");
     }

     @Test
     public void testAltaIndividual() {

          cr.altaIndividual("mera", "Asado3", "Asado a la parrilla3", 225, "");

          cr.seleccionarProducto("Asado3", "mera");

          String expResult = "Asado a la parrilla3";
          String result = cr.verInfoProducto().getDescripcion();
          assertEquals(expResult, result);
          // TODO review the generated test code and remove the default call to fail.
          // fail("The test case is a prototype.");
     }

     /**
      * Test of altaRestaurante method, of class ControladorRestaurantes.
      */
     @Test
     public void testAltaRestaurante() {
          cr.altaRestaurante("Sape", "sape@gmail.com", "sape", "sape 1601", new HashSet<String>());

          boolean expResult = true;
          boolean result = cr.existeRestaurante("Sape");
          assertEquals(expResult, result);
          // TODO review the generated test code and remove the default call to fail.
          // fail("The test case is a prototype.");
     }

     /**
      * Test of altaPromocion method, of class ControladorRestaurantes.
      */
     @Test
     public void testAltaPromocion() {

          cr.seleccionarRestaurante("mera");

          cr.altaPromocion("mera", "Cosa", "Cosas", 20, 666666, "");

          boolean expResult = true;

          boolean result = cr.existeProductoEnRestaurante("Cosa");
          assertEquals(expResult, result);
          // TODO review the generated test code and remove the default call to fail.
          // fail("The test case is a prototype.");
     }

     /**
      * Test of listarCategorias method, of class ControladorRestaurantes.
      */
     @Test
     public void testListarCategorias() {

//          int expResult = 16;
//          int result = cr.listarCategorias().size();
//          assertEquals(expResult, result);
          // TODO review the generated test code and remove the default call to fail.
          // fail("The test case is a prototype.");
     }

     /**
      * Test of listarProductos method, of class ControladorRestaurantes.
      */
     @Test
     public void testListarProductos() {

          System.out.println("ListarProductos");
          boolean expResult = true;
          boolean result = (cr.listarProductos().size() > 18);

          assertEquals(expResult, result);

     }

     /**
      * Test of seleccionarCategoria method, of class ControladorRestaurantes.
      */
     @Test
     public void testSeleccionarCategoria() {
          System.out.println("seleccionarCategoria");
          String nombreCat = "Chivitos";

          cr.seleccionarCategoria(nombreCat);

     }

     /**
      * Test of seleccionarProducto method, of class ControladorRestaurantes.
      */
     @Test
     public void testSeleccionarProducto() {

        
//          String nombreProd = "Asado";
//          String nickRest = "mera";
//          ControladorRestaurantes instance = new ControladorRestaurantes();
//          DataProducto expResult = null;
//          DataProducto result = instance.seleccionarProducto(nombreProd, nickRest);
//          assertEquals(expResult, result);
          // TODO review the generated test code and remove the default call to fail.
          //fail("The test case is a prototype.");
     }

     /**
      * Test of seleccionarRestaurante method, of class ControladorRestaurantes.
      */
     @Test
     public void testSeleccionarRestaurante() {
//          System.out.println("seleccionarRestaurante");
//          String nick = "mera";
//          ControladorRestaurantes instance = new ControladorRestaurantes();
//          instance.seleccionarRestaurante(nick);
          // TODO review the generated test code and remove the default call to fail.
          // fail("The test case is a prototype.");
     }

     /**
      * Test of verInfoRestauranteM method, of class ControladorRestaurantes.
      */
     @Test
     public void testVerInfoRestauranteM() {
          cr.seleccionarRestaurante("mera");

          System.out.println("verInfoRestauranteM");
          ControladorRestaurantes instance = new ControladorRestaurantes();
          String expResult = "mera@hotmail.com";
          String result = cr.verInfoRestauranteM().getMail();
          assertEquals(expResult, result);
          // TODO review the generated test code and remove the default call to fail.
          //  fail("The test case is a prototype.");
     }

     /**
      * Test of listarRestaurantesCategoria method, of class
      * ControladorRestaurantes.
      */
     @Test
     public void testListarRestaurantesCategoria() {
          cr.seleccionarCategoria("Chivitos");
          int expResult = 2;
          int result = cr.listarRestaurantesCategoria().size();
          assertEquals(expResult, result);
          // TODO review the generated test code and remove the default call to fail.
          // fail("The test case is a prototype.");
     }

     /**
      * Test of seleccionarProductoDeRestaurante method, of class
      * ControladorRestaurantes.
      */
     @Test
     public void testSeleccionarProductoDeRestaurante() {
          cr.seleccionarRestaurante("mera");
          cr.seleccionarProductoDeRestaurante("Asado");
          String expResult = "Asado";
          String result = cr.verInfoProducto().getNombre();
                  
          assertEquals(expResult, result);

     }

     /**
      * Test of mostrarPedidosQueLoContienen method, of class
      * ControladorRestaurantes.
      */
     @Test
     public void testMostrarPedidosQueLoContienen() {

          cr.seleccionarProducto("Asado", "mera");
          int expResult = 0;
          int result = cr.mostrarPedidosQueLoContienen().size();
          System.out.println("mostrarPedidosQueLoContienen");

     }

     /**
      * Test of verInfoProducto method, of class ControladorRestaurantes.
      */
     @Test
     public void testVerInfoProducto() {
          System.out.println("verInfoProducto");
          cr.seleccionarProducto("ChiviPizza", "mera");
//          (String nom, String desc, int precio, int tipo, String nombreRes, 
//            String nickRes, String mailRes, String imagen, boolean activada, int descuento, int cant, int precioXL)
          DataProducto dp1 = cr.verInfoProducto();
          DataProducto expResult  = new DataProducto (dp1.getNombre(), dp1.getDescripcion(), dp1.getPrecioUnitario(), dp1.getTipo(), dp1.getNombreRes(), dp1.getNickRes(),
          dp1.getMailRes(), dp1.getImagen(), dp1.getAcivada(), dp1.getDescuento(), dp1.getCantidad(), 0);
          DataProducto result = cr.verInfoProducto();
          
          
          assertEquals(expResult.getNombre(), result.getNombre());
          // TODO review the generated test code and remove the default call to fail.
          // fail("The test case is a prototype.");
     }
}
