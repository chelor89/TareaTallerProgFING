/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Fabrica.Fabrica;
import dataTypes.DataFecha;
import dataTypes.DataProducto;
import dataTypes.Estado;
import interfaces.IControladorClientes;
import interfaces.IControladorPedidos;
import interfaces.IControladorRestaurantes;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Marcelo
 */
//@RunWith(Suite.class)
//@Suite.SuiteClasses({controladores.ControladorClientesTest.class, controladores.ControladorPedidosTest.class, controladores.ControladorRestaurantesTest.class})
public class ControladoresTest {

    Fabrica f = Fabrica.getInstance();

    IControladorPedidos cp = f.getIControladorPedidos();
    IControladorClientes cc = f.getIControladorClientes();
    IControladorRestaurantes cr = f.getIControladorRestaurantes();

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        cc.altaCliente("costas", "gcostas@gmail.com", "Gerardo", "Av. Italia 2078", "Costas", new DataFecha(15, 11, 1983), "francesc_font_2.jpg", new BufferedImage(1,1,1));
        cc.altaCliente("roro", "rcotelo@yahoo.com ", "Rodrigo", "Pdte. Berro 1548", "Cotelo", new DataFecha(2, 8, 1975), "40-300x237.jpg", null);
        cc.altaCliente("chechi", "cgarrido@hotmail.com", "Cecilia", "Gral. Urquiza 1548", "Garrido", new DataFecha(12, 9, 1987), "chechi.jpg", null);
        cc.altaCliente("andy", "agarcia@gmail.com", "Andrea", "Dr. Manuel Albo 4512", "García", new DataFecha(28, 07, 1951), "98871376.jpg", null);
        cc.altaCliente("weiss", "aweiss@hotmail.com ", "Adrian", "Monte Caseros 5615", "Weiss", new DataFecha(23, 12, 1978), "362540d2-e952-48c3-bbea-87e4836415a1.jpg", null);

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

        cr.altaRestaurante("rossell", "bar.rossel@gmail.com", "Bar Rossell", "Bvar. Artigas 1601", new HashSet<BufferedImage>());

        cr.altaRestaurante("bocatti", "bocatti@gmail.com", "Empanadas Bocatti", "18 de julio 2138", null);

        cr.altaRestaurante("winb", "wok.in.box@hotmail.com", "Wok in Box", "Libertad 2535", null);

        cr.altaRestaurante("mera", "mera@hotmail.com", "Pizzeria Mera", "Av 8 de octubre 2704", null);

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

        cr.altaIndividual("mera", "Asado", "Asado a la parrilla", 225, null);
        cr.altaIndividual("mera", "Milanesa de Carne", "Con lechuga, tomate, mayonesa y fritas", 180, null);
        cr.altaIndividual("mera", "Chivito canadiense", "Lomito, jamón, muzza, tomate, aceitunas, panceta, huevo, morrón y fritas", 305, null);
        cr.altaIndividual("mera", "Pizza 2 gustos", "Pizza con dos gustos a elección", 130, null);
        cr.altaIndividual("rossell", "Chivito al plato", "Ensalada rusa, mixta, huevo, jamón, muzza, panceta, aceitunas y fritas", 324, null);
        cr.altaIndividual("rossell", "Milanesa a caballo", "Milanesa con dos huevos fritos acompañado de fritas", 270, null);
        cr.altaIndividual("rossell", "Pizza con 2 gustos", "Pizza con dos gustos a elección", 103, null);
        cr.altaIndividual("rossell", "Agnolotis", "Agnolotis de jamón y queso", 225, null);
        cr.altaIndividual("bocatti", "Empanada de Carne", "Carne, aceitunas, huevo duro, condimentos", 44, null);
        cr.altaIndividual("bocatti", "Empanada Americana", "Carne, panceta y huevo duro", 44, null);
        cr.altaIndividual("bocatti", "Empanada QyC", "Empanada de Queso y Cebolla", 44, null);
        cr.altaIndividual("bocatti", "Empanada Capresse", "Queso, tomate y albahaca", 44, null);
        cr.altaIndividual("winb", "Thai wok", "Cerdo, calamares, sweet chili, salsa de ostras, maní y jugo de lima, acompañado de tallarines o arroz.", 240, null);
        cr.altaIndividual("winb", "China wok", "Tempura de cerdo, vegetales mixtos, almendras, salsa de soja y naranja, acompañado de tallarines o arroz.", 240, null);
        cr.altaIndividual("winb", "Classic wok de pollo", "Pollo, vegetales mixtos, salsa agridulce, salsa de soja y cebollita de verdeo, acompañado de tallarines o arroz.", 230, null);
        cr.altaIndividual("winb", "Classic wok de cerdo", "Cerdo, vegetales mixtos, jengibre, salsa de ostras y ralladura de lima, acompañado de tallarines o arroz.", 230, null);

        cr.seleccionarRestaurante("mera");

        cr.altaPromocion("mera", "ChiviPizza", "Chivito y Pizza", 20, 666666, null);
        cr.seleccionarProductoDeRestaurante("ChiviPizza");
        cr.agregarIndividualAPromocion("Chivito canadiense", 1);
        cr.agregarIndividualAPromocion("Pizza 2 gustos", 1);

        cr.altaPromocion("mera", "MilaAsado", "3 Milanesas + 1 Asado para compartir", 30, 666666, null);
        cr.seleccionarProductoDeRestaurante("MilaAsado");
        cr.agregarIndividualAPromocion("Milanesa de Carne", 3);
        cr.agregarIndividualAPromocion("Asado", 1);

        cr.seleccionarRestaurante("rossell");
        cr.altaPromocion("rossell", "MilaPizza", "2 Milanesas a caballo + 1 Pizzas 2 gustos", 10, 666666, null);
        cr.seleccionarProductoDeRestaurante("MilaPizza");
        cr.agregarIndividualAPromocion("Milanesa a caballo", 2);
        cr.agregarIndividualAPromocion("Pizza con 2 gustos", 1);

        cp.SeleccionarCliente("chechi");
        cp.altaPedido(new DataFecha(2014, 8, 19));
        cp.SeleccionarRestaurante("winb");
        cp.AgregarPedidoACliente();
        cp.SeleccionarProducto("Thai wok");
        cp.agregarProducto(2);
        cp.SeleccionarProducto("China wok");
        cp.agregarProducto(2);
        cp.actualizarEstadoPedido(Estado.Recibido);
       

        cp.SeleccionarCliente("andy");
        cp.altaPedido(new DataFecha(2014, 8, 22));
        cp.SeleccionarRestaurante("mera");
        cp.AgregarPedidoACliente();
        cp.SeleccionarProducto("Chivito canadiense");
        cp.agregarProducto(4);
        cp.actualizarEstadoPedido(Estado.Recibido);

        cp.SeleccionarCliente("weiss");
        cp.altaPedido(new DataFecha(2014, 8, 25));
        cp.SeleccionarRestaurante("rossell");
        cp.AgregarPedidoACliente();
        cp.SeleccionarProducto("Agnolotis");
        cp.agregarProducto(1);
        cp.actualizarEstadoPedido(Estado.Recibido);
        cp.AgregarComentarioAPedido("Los Agnolotis llegaron un poco fríos y demoraron más de la cuenta. Espero mejoren. De"
                + "todas formas, muy ricos.", 3, new DataFecha(26, 8, 2014));

        cp.SeleccionarCliente("roro");
        cp.altaPedido(new DataFecha(2014, 9, 20));
        cp.SeleccionarRestaurante("mera");
        cp.AgregarPedidoACliente();
        cp.SeleccionarProducto("Chivito canadiense");
        cp.agregarProducto(1);
        cp.SeleccionarProducto("Milanesa de Carne");
        cp.agregarProducto(1);
        cp.actualizarEstadoPedido(Estado.Recibido);
        cp.AgregarComentarioAPedido("Tanto la milanesa como el chivito llegaron 3 horas tarde!, obviamente helados!!, un"
                + "desastre, nunca más pido ahí", 1, new DataFecha(21, 9, 2014));

        cp.SeleccionarCliente("andy");
        cp.altaPedido(new DataFecha(2014, 8, 22));
        cp.SeleccionarRestaurante("rossell");
        cp.AgregarPedidoACliente();
        cp.SeleccionarProducto("Chivito al plato");
        cp.agregarProducto(2);
        cp.SeleccionarProducto("Milanesa a caballo");
        cp.agregarProducto(1);
        cp.actualizarEstadoPedido(Estado.Recibido);
        cp.AgregarComentarioAPedido("Todo llegó en hora. El chivito y la milanesa a caballo estaban un poco aceitosos, pero más"
                + "allá de eso se comió rico y en abundancia.", 4, new DataFecha(3, 10, 2014));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAltaCliente() {
        int expResult = cc.listarClientes().size() + 1;
        cc.altaCliente("Sape2", "Sap2e@gmail.com", "Sape", "Sape 2078", "Sape", new DataFecha(15, 11, 1983), "francesc_font_2.jpg", null);
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
        cc.altaCliente("Sape", "Sape@gmail.com", "Sape", "Sape 2078", "Sape", new DataFecha(15, 11, 1983), "francesc_font_2.jpg", null);
        cc.seleccionarCliente("Sape");
        assertEquals("Sape@gmail.com", cc.verInfoCliente().getMail());

    }

    @Test
    public void testAgregarProducto() {
        System.out.println("agregarProducto");

        // TODO review the generated test code and remove the default call to fail.
        //  fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarEstadoPedido method, of class ControladorPedidos.
     */
    @Test
    public void testActualizarEstadoPedido() {
        cp.seleccionarPedido(2);
        cp.actualizarEstadoPedido(Estado.Recibido);
        Estado result = cp.verInfoPedido().getEstado();
        Estado expResult = Estado.Recibido;
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of cancelarPedidoM method, of class ControladorPedidos.
     */
    @Test
    public void testCancelarPedidoM() {

        cp.SeleccionarCliente("weiss");
        cp.altaPedido();
        cp.SeleccionarRestaurante("mera");
        cp.AgregarPedidoACliente();
        cp.SeleccionarProducto("ChiviPizza");
        cp.agregarProducto(1);

        int expResult = cp.listarPedidos().size() - 1;
        cp.seleccionarPedido(cp.verInfoPedidoGenerado().getId());
        cp.cancelarPedidoM();
        int result = cp.listarPedidos().size();

        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //  fail("The test case is a prototype.");
    }

    /**
     * Test of listarClientes method, of class ControladorPedidos.
     */
    @Test
    public void testListarClientesP() {
        boolean expResult = true;
        boolean result = (cp.listarClientes().size() > 0);
        assertEquals(expResult, result);

    }

    /**
     * Test of listarCategorias method, of class ControladorPedidos.
     */
    @Test
    public void testListarCategorias() {
        System.out.println("listarCategorias");
        boolean expResult = true;
        boolean result = (cp.listarCategorias().size() > 0);
        assertEquals(expResult, result);
    }

    @Test
    public void testListarProductosDelRestaurante() {
//          System.out.println("listarProductosDelRestaurante");
        boolean expResult = true;
        cp.SeleccionarRestaurante("mera");
        boolean result = (cp.listarProductosDelRestaurante(2).size() > 0);
        assertEquals(expResult, result);
    }

    /**
     * Test of listarRestaurantesCategoria method, of class ControladorPedidos.
     */
    @Test
    public void testListarRestaurantesCategoria() {
        cp.seleccionarCategoria("Chivitos");
        int expResult = 2;
        int result = cp.listarRestaurantesCategoria().size();
        assertEquals(expResult, result);
        assertEquals(cr.listarRestaurantes().size(), 4);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of verInfoPedido method, of class ControladorPedidos.
     */
    @Test
    public void testVerInfoPedido() {
        cp.seleccionarPedido(1);
        int result = cp.verInfoPedido().getId();
        int expResult = 1;
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of verInfoPedidoGenerado method, of class ControladorPedidos.
     */
    @Test
    public void testVerInfoPedidoGenerado() {
        String expResult = "rossell";
        cp.SeleccionarCliente("weiss");
        cp.SeleccionarRestaurante("rossell");
        int pedidos = cr.listarPedidosRestaurante("rossell").size();
        cp.altaPedido();
      
        
        cp.AgregarPedidoACliente();
        cp.SeleccionarProducto("Agnolotis");
        cp.agregarProducto(1);

        String result = cp.verInfoPedidoGenerado().getNickRestaurante();
        //   String result = cp.verInfoPedidoGenerado().getCliente().getNick();
        assertEquals(expResult, result);
        assertEquals(pedidos +1, cr.listarPedidosRestaurante("rossell").size());
        // TODO review the generated test code and remove the default call to fail.
        //  fail("The test case is a prototype.");
    }
    @Test
    public void testActualizarIndividual() {

        String nombre = "Asado2";
        String descripcion = "Asado a la parrilla";
        String imagen = "";
        int precio = 225;

        cr.seleccionarProducto("Asado", "mera");
        cr.actualizarIndividual(nombre, descripcion, null, precio);
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
        cr.actualizarPromocion(nombre, descripcion, null, activar, descuento);

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

        int expResult = cr.verInfoRestauranteM().getProductos(3).size() + 1;

        cr.altaPromocion("rossell", "MilaPizza2", "2 Milanesas a caballo + 1 Pizzas 2 gustos", 10, 0, null);
        cr.seleccionarProductoDeRestaurante("MilaPizza2");
        cr.agregarIndividualAPromocion("Milanesa a caballo", 2);
        cr.agregarIndividualAPromocion("Pizza con 2 gustos", 1);

        int result = cr.verInfoRestauranteM().getProductos(3).size();

        System.out.println("agregarIndividualAPromocion");

        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    @Test
    public void testAltaIndividual() {

        cr.altaIndividual("mera", "Asado3", "Asado a la parrilla3", 225, null);

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
        cr.altaRestaurante("Sape", "sape@gmail.com", "sape", "sape 1601", null);

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

        cr.altaPromocion("mera", "Cosa", "Cosas", 20, 666666, null);

        boolean expResult = true;

        boolean result = cr.existeProductoEnRestaurante("Cosa");
        assertEquals(expResult, result);
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
        String expResult = dp1.getNombre();
        DataProducto result = cr.verInfoProducto();

        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
    @Test
    public void testAgregarComentarioAPedido() {
        
        cp.seleccionarPedido(2);
        cr.seleccionarRestaurante(cp.verInfoPedido().getNickRestaurante());
        int expresult = cr.listarComentarios().size() + 1;
        cp.AgregarComentarioAPedido("Comentario", 3);
        
        cp.seleccionarPedido(2); 
      
        assertEquals(expresult, cr.listarComentarios().size());
        assertEquals("Comentario", cp.verInfoPedido().getComentario().getContenido());

    }
}
