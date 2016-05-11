
<%@page import="com.quickorder.controllers.Imagenes"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.io.File"%>
<%@page import="dataTypes.Estado"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="interfaces.IControladorPedidos"%>
<%@page import="interfaces.IControladorRestaurantes"%>
<%@page import="Fabrica.Fabrica"%>
<%@page import="dataTypes.DataFecha"%>
<%@page import="interfaces.IControladorClientes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
      <%  
      %>
        <% 
        Fabrica F = Fabrica.getInstance();
        IControladorClientes cc  = F.getIControladorClientes();
        IControladorRestaurantes cr  = F.getIControladorRestaurantes();
        IControladorPedidos cp  = F.getIControladorPedidos();
        
        String ruta = "/media/images/casosprueba/";
        String path = request.getServletContext().getRealPath("");
        String ruta_imagenes = path + ruta;
            

            
        BufferedImage img1 = Imagenes.cargarImagen( ruta_imagenes + "francesc_font_2.jpg");
        BufferedImage img2 = Imagenes.cargarImagen( ruta_imagenes + "40-300x237.jpg");
        BufferedImage img3 = Imagenes.cargarImagen( ruta_imagenes + "542179_374050182661087_570456924_n.jpg");
        BufferedImage img4 = Imagenes.cargarImagen( ruta_imagenes + "98871376.jpg");
        BufferedImage img5 = Imagenes.cargarImagen( ruta_imagenes + "362540d2-e952-48c3-bbea-87e4836415a1.jpg");
        cc.altaCliente("costas", "gcostas@gmail.com", "Gerardo", "Av. Italia 2078", "Costas", new DataFecha(15, 11, 1983),"costas123", img1);
        cc.altaCliente("roro", "rcotelo@yahoo.com ", "Rodrigo", "Pdte. Berro 1548", "Cotelo", new DataFecha(2, 8, 1975),"12elroro", img2);
        cc.altaCliente("chechi", "cgarrido@hotmail.com", "Cecilia", "Gral. Urquiza 1548", "Garrido", new DataFecha(12, 9, 1987),"ch4321",img3);
        cc.altaCliente("andy", "agarcia@gmail.com", "Andrea", "Dr. Manuel Albo 4512", "García", new DataFecha(28, 07, 1951),"andy_la1", img4);
        cc.altaCliente("weiss", "aweiss@hotmail.com ", "Adrian", "Monte Caseros 5615", "Weiss", new DataFecha(23, 12, 1978),"223_aweis", img5);
        
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
           
        Set<BufferedImage> imgs1 = new HashSet<BufferedImage>();
        imgs1.add(Imagenes.cargarImagen( ruta_imagenes + "BARROSSELL.png"));
        imgs1.add(Imagenes.cargarImagen( ruta_imagenes + "bar-rossell-logo.jpg"));        
        cr.altaRestaurante("rossell", "bar.rossel@gmail.com", "Bar Rossell", "Bvar. Artigas 1601", imgs1);
        
        Set<BufferedImage> imgs2 = new HashSet<BufferedImage>();
        imgs2.add(Imagenes.cargarImagen( ruta_imagenes + "bocatti-empanadas.jpg"));
        imgs2.add(Imagenes.cargarImagen( ruta_imagenes + "10259932_748842808489816_4942781320214052509_n.jpg"));
        imgs2.add(Imagenes.cargarImagen( ruta_imagenes + "10388594_755411944499569_8836515887985101946_n.png"));        
        cr.altaRestaurante("bocatti", "bocatti@gmail.com", "Empanadas Bocatti", "18 de julio 2138", imgs2);
        
        Set<BufferedImage> imgs3 = new HashSet<BufferedImage>();
        imgs3.add(Imagenes.cargarImagen( ruta_imagenes + "wok-in-box.jpg"));
        imgs3.add(Imagenes.cargarImagen( ruta_imagenes + "wokboxrestaurant.jpg"));        
        cr.altaRestaurante("winb", "wok.in.box@hotmail.com", "Wok in Box", "Libertad 2535", imgs3);
        
        cr.altaRestaurante("mera", "mera@hotmail.com", "Pizzeria Mera", "Av 8 de octubre 2704", (new HashSet<BufferedImage>()));
           
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
        cr.altaIndividual("mera", "Milanesa de Carne", "Con lechuga, tomate, mayonesa y fritas", 180, Imagenes.cargarImagen( ruta_imagenes + "milanesa-con-fritas.jpg"));
        cr.altaIndividual("mera", "Chivito canadiense", "Lomito, jamón, muzza, tomate, aceitunas, panceta, huevo, morrón y fritas", 305, Imagenes.cargarImagen( ruta_imagenes + "chivito.jpg"));
        cr.altaIndividual("mera", "Pizza 2 gustos", "Pizza con dos gustos a elección", 130, null);
        cr.altaIndividual("rossell", "Chivito al plato", "Ensalada rusa, mixta, huevo, jamón, muzza, panceta, aceitunas y fritas", 324, null);
        cr.altaIndividual("rossell", "Milanesa a caballo", "Milanesa con dos huevos fritos acompañado de fritas", 270, Imagenes.cargarImagen( ruta_imagenes + "215751-sabores450-thumb.jpg"));
        cr.altaIndividual("rossell", "Pizza con 2 gustos", "Pizza con dos gustos a elección", 103, null);
        cr.altaIndividual("rossell", "Agnolotis", "Agnolotis de jamón y queso", 225, Imagenes.cargarImagen( ruta_imagenes + "sorrentinos-caprese.jpg"));
        cr.altaIndividual("bocatti", "Empanada de Carne", "Carne, aceitunas, huevo duro, condimentos", 44, Imagenes.cargarImagen( ruta_imagenes + "empanada_argentina.jpg"));
        cr.altaIndividual("bocatti", "Empanada Americana", "Carne, panceta y huevo duro", 44, null);
        cr.altaIndividual("bocatti", "Empanada QyC", "Empanada de Queso y Cebolla", 44, Imagenes.cargarImagen( ruta_imagenes + "cebollayqueso.jpg"));
        cr.altaIndividual("bocatti", "Empanada Capresse", "Queso, tomate y albahaca", 44, Imagenes.cargarImagen( ruta_imagenes + "Caprese.jpg"));
        cr.altaIndividual("winb", "Thai wok", "Cerdo, calamares, sweet chili, salsa de ostras, maní y jugo de lima, acompañado de tallarines o arroz.", 240, Imagenes.cargarImagen( ruta_imagenes + "wok-verduras-thai-contoque-paprika-300x171.jpg"));
        cr.altaIndividual("winb", "China wok", "Tempura de cerdo, vegetales mixtos, almendras, salsa de soja y naranja, acompañado de tallarines o arroz.", 240, null);
        cr.altaIndividual("winb", "Classic wok de pollo", "Pollo, vegetales mixtos, salsa agridulce, salsa de soja y cebollita de verdeo, acompañado de tallarines o arroz.", 230, Imagenes.cargarImagen( ruta_imagenes + "images.jpg"));
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
        
        cp.SeleccionarCliente("costas");
        cp.altaPedido(new DataFecha(2014, 8, 12));
        cp.SeleccionarRestaurante("bocatti");        
        cp.AgregarPedidoACliente();
            cp.SeleccionarProducto("Empanada de Carne");
            cp.agregarProducto(1);
            cp.SeleccionarProducto("Empanada Americana");
            cp.agregarProducto(2);
            cp.SeleccionarProducto("Empanada QyC");
            cp.agregarProducto(2);
            
        cp.SeleccionarCliente("roro");
        cp.altaPedido(new DataFecha(2014, 8, 19));
        cp.SeleccionarRestaurante("mera");        
        cp.AgregarPedidoACliente();
            cp.SeleccionarProducto("Asado");
            cp.agregarProducto(3);
            cp.actualizarEstadoPedido(Estado.Enviado);
        
        cp.SeleccionarCliente("chechi");
        cp.altaPedido(new DataFecha(2014, 8, 19));
        cp.SeleccionarRestaurante("winb");        
        cp.AgregarPedidoACliente();
            cp.SeleccionarProducto("Thai wok");
            cp.agregarProducto(2);
            cp.SeleccionarProducto("China wok");
            cp.agregarProducto(2);
            cp.actualizarEstadoPedido(Estado.Recibido);
            cp.AgregarComentarioAPedido("Si bien el thai wok y el china wok están ricos, me cobraron 60$ de envío y eso no estaba"+
                                            "aclarado y no pueden hacerlo. Me dejó muy molesto.", 2, new DataFecha(2014, 8, 20));
        
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
        cp.AgregarComentarioAPedido("Los Agnolotis llegaron un poco fríos y demoraron más de la cuenta. Espero mejoren. De"+
                                        "todas formas, muy ricos." , 3, new DataFecha(26, 8, 2014));
            
        cp.SeleccionarCliente("roro");
        cp.altaPedido(new DataFecha(2014, 9, 20));
        cp.SeleccionarRestaurante("mera");        
        cp.AgregarPedidoACliente();
            cp.SeleccionarProducto("Chivito canadiense");
            cp.agregarProducto(1);
            cp.SeleccionarProducto("Milanesa de Carne");
            cp.agregarProducto(1);
            cp.actualizarEstadoPedido(Estado.Recibido);
            cp.AgregarComentarioAPedido("Tanto la milanesa como el chivito llegaron 3 horas tarde!, obviamente helados!!, un"+
                                            "desastre, nunca más pido ahí", 1, new DataFecha(21, 9, 2014));
            
        cp.SeleccionarCliente("andy");
        cp.altaPedido(new DataFecha(2014, 8, 22));
        cp.SeleccionarRestaurante("rossell");        
        cp.AgregarPedidoACliente();
            cp.SeleccionarProducto("Chivito al plato"); 
            cp.agregarProducto(2);
            cp.SeleccionarProducto("Milanesa a caballo"); 
            cp.agregarProducto(1);
            cp.actualizarEstadoPedido(Estado.Recibido); 
        cp.AgregarComentarioAPedido("Todo llegó en hora. El chivito y la milanesa a caballo estaban un poco aceitosos, pero más"+
                                        "allá de eso se comió rico y en abundancia.", 4, new DataFecha(3, 10, 2014));
        %>
        <title>QuickOrder - La mejor forma de morfar</title>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta http-equiv="refresh" content="0; url=/casa">
    </head>
    <body>
        <h4 style="color:red">cargando datos...</h4>
    </body>
</html>