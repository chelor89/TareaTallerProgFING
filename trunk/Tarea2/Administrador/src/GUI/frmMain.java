/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Fabrica.Fabrica;
import dataTypes.DataFecha;
import dataTypes.Estado;
import static entidades.Imagenes.leerImgURL;
import interfaces.IControladorClientes;
import interfaces.IControladorPedidos;
import interfaces.IControladorRestaurantes;
import java.awt.Component;
import java.awt.TextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

/**
 *
 * @author Fede
 */

 
public class frmMain extends javax.swing.JFrame {
    String ruta = System.getProperty("user.dir") + "\\ImagenesPrueba\\";
    Fabrica F = Fabrica.getInstance();
    IControladorClientes cc  = F.getIControladorClientes();
    IControladorRestaurantes cr  = F.getIControladorRestaurantes();
    IControladorPedidos cp  = F.getIControladorPedidos();
    
    boolean datosCargados = false;
    
    String imagenSeleccionada;
    
    public frmMain() {
        
        initComponents();
    }

private void CargarDatos() throws IOException{
        BufferedImage img1 = leerImgURL(ruta + "francesc_font_2.jpg");
        BufferedImage img2 = leerImgURL(ruta + "40-300x237.jpg");
        BufferedImage img3 = leerImgURL(ruta + "542179_374050182661087_570456924_n.jpg");
        BufferedImage img4 = leerImgURL(ruta + "98871376.jpg");
        BufferedImage img5 = leerImgURL(ruta + "362540d2-e952-48c3-bbea-87e4836415a1.jpg");
        cc.altaCliente("costas", "gcostas@gmail.com", "Gerardo", "Av. Italia 2078", "Costas", new DataFecha(15, 11, 1983),"", img1);
        cc.altaCliente("roro", "rcotelo@yahoo.com ", "Rodrigo", "Pdte. Berro 1548", "Cotelo", new DataFecha(2, 8, 1975),"", img2);
        cc.altaCliente("chechi", "cgarrido@hotmail.com", "Cecilia", "Gral. Urquiza 1548", "Garrido", new DataFecha(12, 9, 1987),"",img3);
        cc.altaCliente("andy", "agarcia@gmail.com", "Andrea", "Dr. Manuel Albo 4512", "García", new DataFecha(28, 07, 1951),"", img4);
        cc.altaCliente("weiss", "aweiss@hotmail.com ", "Adrian", "Monte Caseros 5615", "Weiss", new DataFecha(23, 12, 1978),"", img5);
        
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
           
        Set<BufferedImage> imgs1 = new HashSet<>();
        imgs1.add(leerImgURL(ruta + "BARROSSELL.png"));
        imgs1.add(leerImgURL(ruta + "bar-rossell-logo.jpg"));        
        cr.altaRestaurante("rossell", "bar.rossel@gmail.com", "Bar Rossell", "Bvar. Artigas 1601", imgs1);
        
        Set<BufferedImage> imgs2 = new HashSet<>();
        imgs2.add(leerImgURL(ruta + "bocatti-empanadas.jpg"));
        imgs2.add(leerImgURL(ruta + "10259932_748842808489816_4942781320214052509_n.jpg"));
        imgs2.add(leerImgURL(ruta + "10388594_755411944499569_8836515887985101946_n.png"));        
        cr.altaRestaurante("bocatti", "bocatti@gmail.com", "Empanadas Bocatti", "18 de julio 2138", imgs2);
        
        Set<BufferedImage> imgs3 = new HashSet<>();
        imgs3.add(leerImgURL(ruta + "wok-in-box.jpg"));
        imgs3.add(leerImgURL(ruta + "wokboxrestaurant.jpg"));        
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
        BufferedImage imgI1 = leerImgURL(ruta + "milanesa-con-fritas.jpg");
        cr.altaIndividual("mera", "Milanesa de Carne", "Con lechuga, tomate, mayonesa y fritas", 180, imgI1);
        BufferedImage imgI2 = leerImgURL(ruta + "chivito.jpg");
        cr.altaIndividual("mera", "Chivito canadiense", "Lomito, jamón, muzza, tomate, aceitunas, panceta, huevo, morrón y fritas", 305, imgI2);
        cr.altaIndividual("mera", "Pizza 2 gustos", "Pizza con dos gustos a elección", 130, null);
        cr.altaIndividual("rossell", "Chivito al plato", "Ensalada rusa, mixta, huevo, jamón, muzza, panceta, aceitunas y fritas", 324, null);
        BufferedImage imgI3 = leerImgURL(ruta + "215751-sabores450-thumb.jpg");
        cr.altaIndividual("rossell", "Milanesa a caballo", "Milanesa con dos huevos fritos acompañado de fritas", 270, imgI3);
        cr.altaIndividual("rossell", "Pizza con 2 gustos", "Pizza con dos gustos a elección", 103, null);
        //BufferedImage imgI4 = ImageIO.read(new File(ruta + "sorrentinos-caprese.jpg"));
        cr.altaIndividual("rossell", "Agnolotis", "Agnolotis de jamón y queso", 225, null);
        BufferedImage imgI5 = leerImgURL(ruta + "empanada_argentina.jpg");
        cr.altaIndividual("bocatti", "Empanada de Carne", "Carne, aceitunas, huevo duro, condimentos", 44, imgI5);
        cr.altaIndividual("bocatti", "Empanada Americana", "Carne, panceta y huevo duro", 44, null);
        BufferedImage imgI6 = leerImgURL(ruta + "cebollayqueso.jpg");
        cr.altaIndividual("bocatti", "Empanada QyC", "Empanada de Queso y Cebolla", 44, imgI6);
        BufferedImage imgI7 = leerImgURL(ruta + "Caprese.jpg");
        cr.altaIndividual("bocatti", "Empanada Capresse", "Queso, tomate y albahaca", 44, imgI7);
        BufferedImage imgI8 = leerImgURL(ruta + "wok-verduras-thai-contoque-paprika-300x171.jpg");
        cr.altaIndividual("winb", "Thai wok", "Cerdo, calamares, sweet chili, salsa de ostras, maní y jugo de lima, acompañado de tallarines o arroz.", 240, imgI8 );
        cr.altaIndividual("winb", "China wok", "Tempura de cerdo, vegetales mixtos, almendras, salsa de soja y naranja, acompañado de tallarines o arroz.", 240, null);
        BufferedImage imgI9 = leerImgURL(ruta + "images.jpg");
        cr.altaIndividual("winb", "Classic wok de pollo", "Pollo, vegetales mixtos, salsa agridulce, salsa de soja y cebollita de verdeo, acompañado de tallarines o arroz.", 230, imgI9 );
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
            cp.agregarProducto(3);
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
        
        
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        MenuBar = new javax.swing.JMenuBar();
        menuInicio = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        menuRegistros = new javax.swing.JMenu();
        menuRegistroCliente = new javax.swing.JMenuItem();
        menuRegistroRestaurante = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        menuInformacion = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        menuInicio.setText("Inicio");
        menuInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInicioActionPerformed(evt);
            }
        });

        jMenuItem5.setText("Cargar datos");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        menuInicio.add(jMenuItem5);

        jMenuItem1.setText("Alta categoria");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuInicio.add(jMenuItem1);

        jMenuItem6.setText("Actualizar producto");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        menuInicio.add(jMenuItem6);

        jMenuItem7.setText("Cancelar pedido");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        menuInicio.add(jMenuItem7);

        jMenuItem10.setText("Actualizar estado de pedido");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        menuInicio.add(jMenuItem10);

        MenuBar.add(menuInicio);

        menuRegistros.setText("Registros");
        menuRegistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegistrosActionPerformed(evt);
            }
        });

        menuRegistroCliente.setText("Registro Cliente");
        menuRegistroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegistroClienteActionPerformed(evt);
            }
        });
        menuRegistros.add(menuRegistroCliente);

        menuRegistroRestaurante.setText("Registro Restaurante");
        menuRegistroRestaurante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegistroRestauranteActionPerformed(evt);
            }
        });
        menuRegistros.add(menuRegistroRestaurante);

        jMenuItem9.setText("Registro Producto");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        menuRegistros.add(jMenuItem9);

        MenuBar.add(menuRegistros);

        menuInformacion.setText("Información");

        jMenuItem2.setText("Ver informacion producto");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuInformacion.add(jMenuItem2);

        jMenuItem4.setText("Ver informacion cliente");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menuInformacion.add(jMenuItem4);

        jMenuItem3.setText("Ver informacion restaurante");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menuInformacion.add(jMenuItem3);

        jMenuItem8.setText("Ver informacion pedido");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        menuInformacion.add(jMenuItem8);

        MenuBar.add(menuInformacion);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 912, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 643, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       AltaCategoria ac = new AltaCategoria();
       
       //this.getParent().add(ac,1);
       this.add(ac);
       ac.show();
       ac.setVisible(true); 
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menuRegistroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegistroClienteActionPerformed
       RegistrarCliente rc = new RegistrarCliente();
       this.add(rc);
       //this.getParent().add(rc,1);
       rc.show();
       rc.setVisible(true);   
    }//GEN-LAST:event_menuRegistroClienteActionPerformed

    private void menuRegistroRestauranteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegistroRestauranteActionPerformed
        if(cr.listarCategorias().size() > 0){
            RegistrarRestaurante rr = new RegistrarRestaurante();
            this.add(rr);
            //this.getParent().add(rr,1);
            rr.show();
        }     
        else
            JOptionPane.showMessageDialog(null, "Debe existir por lo menos una Categoria en el sistema");
      
    }//GEN-LAST:event_menuRegistroRestauranteActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        VerInfoRestaurante vr = new VerInfoRestaurante();
        this.add(vr);
        //this.getParent().add(vr,1);
        vr.show();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        VerInfoProducto vp = new VerInfoProducto();
        this.add(vp);
        //this.getParent().add(vp,1);
        vp.show();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
       RegistrarProducto rp = new RegistrarProducto();
       this.add(rp);  
       //this.getParent().add(rp,1);
       rp.show();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void menuRegistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegistrosActionPerformed

    }//GEN-LAST:event_menuRegistrosActionPerformed

    private void menuInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuInicioActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
       ActualizarProducto ap = new ActualizarProducto();
       this.add(ap);
       //this.getParent().add(ap,1);
       ap.show();
       ap.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
       if (!datosCargados){
           datosCargados = true;
//           ruta = JOptionPane.showInputDialog(null, "Ruta:", "Ruta", INFORMATION_MESSAGE);           
           try {
               CargarDatos();
           } catch (IOException ex) {
               Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
    
        ActualizarEstadoPedido aep = new ActualizarEstadoPedido();
       this.add(aep);
       //this.getParent().add(aep,1);
       aep.show();
       aep.setVisible(true);  
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        VerInfoPedido vp = new VerInfoPedido(-2);
        this.add(vp);
        //this.getParent().add(vp,1);
        vp.show();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

        VerInfoCliente vc = new VerInfoCliente();
        this.add(vc);
        //this.getParent().add(vc,1);
        vc.show();
        vc.setVisible(true);       
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        VerInfoPedido vp = new VerInfoPedido(-1);
        this.add(vp);
        //this.getParent().add(vp,1);
        vp.show();
        
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenu menuInformacion;
    private javax.swing.JMenu menuInicio;
    private javax.swing.JMenuItem menuRegistroCliente;
    private javax.swing.JMenuItem menuRegistroRestaurante;
    private javax.swing.JMenu menuRegistros;
    // End of variables declaration//GEN-END:variables
}
