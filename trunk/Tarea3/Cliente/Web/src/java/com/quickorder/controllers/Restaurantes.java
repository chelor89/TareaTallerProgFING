/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickorder.controllers;

import static com.quickorder.controllers.Sesion.getInfoClienteLogueado;
import com.quickorder.model.Carrito;
import com.quickorder.model.ProductoPedido;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.java.dev.jaxb.array.IntArray;
import net.java.dev.jaxb.array.StringArray;
import publicar.DataCliente;
import publicar.DataPedidoArray;
import publicar.DataProducto;
import publicar.DataRestaurante;
import publicar.PublicadorRestaurantes;
import publicar.PublicadorRestaurantesService;

/**
 *
 * @author Marcelo
 */
public class Restaurantes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws java.net.MalformedURLException
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws javax.mail.MessagingException
     * @throws com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws MalformedURLException, ServletException, IOException, javax.mail.MessagingException, MessagingException
             {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");

        String tipo = request.getParameter("tipo");
        String producto = request.getParameter("producto");
        String restaurante = request.getParameter("restaurante");
        String cantidad = request.getParameter("cantidad");

     
        
        URL url = new URL("http://" + (request.getSession().getAttribute("wsurl") + "/publicadorRestaurantes?wsdl"));
      
        PublicadorRestaurantesService service = new PublicadorRestaurantesService(url);
        PublicadorRestaurantes port = service.getPublicadorRestaurantesPort();

        switch (tipo) {

            case "pagRestaurante":
                DataRestaurante dr = port.getRestauranteCR(restaurante);
                DataPedidoArray dpe = port.getPedidosRestaurante(restaurante);
                request.setAttribute("restaurante", dr);
                request.setAttribute("pedidos", dpe);
                request.getRequestDispatcher("/WEB-INF/restaurante/detalles.jsp").forward(request, response);
                break;

            case "getProducto":
                DataProducto dp = port.getProductoCR(producto);
                ProductoPedido pp = new ProductoPedido(dp, 0);
                response.getWriter().write(pp.toJsonText());
                break;

            case "addProducto":
                Carrito C = (Carrito) request.getSession().getAttribute("carrito");
                dp = port.getProductoCR(producto);
                pp = new ProductoPedido(dp, Integer.valueOf(cantidad));
                //Si se cambia de restaurante se borra el pedido en curso y se inicia de nuevo
                if (!restaurante.equals(C.getRestaurante())) {
                    C.clear();
                }
                //Si el restaurante no esta seteado se setea en el actual
                if (C.getRestaurante().equals("")) {
                    C.setRestaurante(restaurante);
                }
                //Se agrega el producto al pedido
                C.addProducto(pp);
                request.getSession().setAttribute("carrito", C);
                break;

            case "removeProducto":
                C = (Carrito) request.getSession().getAttribute("carrito");
                C.removeProducto(producto);
                request.setAttribute("carrito", C);
                break;

            case "containsProducto":
                C = (Carrito) request.getSession().getAttribute("carrito");
                boolean aux = C.containsProducto(producto);
                response.getWriter().write(Boolean.toString(aux));
                break;

            case "getPedido":
                C = (Carrito) request.getSession().getAttribute("carrito");
                response.getWriter().write(C.toJsonText());
                break;

            case "generarPedido":
                C = (Carrito) request.getSession().getAttribute("carrito");
                DataCliente cliente = Sesion.getInfoClienteLogueado(request);
                StringArray saN = new StringArray();
                IntArray iaC = new IntArray();

                for (ProductoPedido prp : C.getProductos()) {
                    saN.getItem().add(prp.getNombre());
                    iaC.getItem().add(prp.getCantidad());
                }
                //Obtiene el Nombre del Restaurante para el email
                dr = (DataRestaurante) port.getRestauranteCR(C.getRestaurante());
                String nomRest = dr.getNombre();
                //Envia el email
                enviarEmail(getInfoClienteLogueado(request), C, nomRest);

                port.generarPedido(C.getRestaurante(), saN, iaC, cliente.getNick());

                C.clear();
                break;
        }
    }

    public void enviarEmail(DataCliente dc, Carrito C, String nomRest) throws javax.mail.MessagingException, MessagingException, UnknownHostException {

        // Direccion que recibe el mensaje
        String to = dc.getMail();

        // Direccion de la que se envia el mensaje
        String from = "do_not_reply@quickorder.com";

        String host = "localhost";

        // Obtiene las propiedades del sistema
        Properties properties = System.getProperties();

        // Setea el servidor de mail
        properties.setProperty("mail.smtp.host", host);

        properties.setProperty("mail.smtp.port", "25");

        // Obtiene la Sesion
        Session session = Session.getDefaultInstance(properties);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(to));

        Date d = new Date();
        SimpleDateFormat formA = new SimpleDateFormat("yyyy");
        SimpleDateFormat formM = new SimpleDateFormat("MM");
        SimpleDateFormat formD = new SimpleDateFormat("dd");
        SimpleDateFormat formH = new SimpleDateFormat("hh");
        SimpleDateFormat formMM = new SimpleDateFormat("mm");
        message.setSentDate(d);
        //Setea el asunto  
        message.setSubject("[Quick Order][" + formD.format(d) + "/" + formM.format(d)
                + "/" + formA.format(d) + " " + formH.format(d) + ":" + formMM.format(d) + "]");
        //Crea el mensaje
        String mensaje
                = "<p>Estimado" + dc.getNombre() + ",</p>\n"
                + "\n"
                + "<p>&nbsp;Su pedido ha sido recibido con &eacute;xito:<br />\n"
                + "---Detalles del Pedido<br />\n"
                + "<strong>Productos:</strong></p>\n"
                + "\n"
                + "<blockquote>";
        //Se agregan los productos del pedido
        int precio = 0;
        for (ProductoPedido p : C.getProductos()) {
            mensaje = mensaje + "-Nombre:" + p.getNombre() + " &ndash; Tipo: " + p.getTipo() + " &ndash; Cantidad: " + p.getCantidad() + " &ndash; PU:$" + p.getPrecio() + " LV:$" + p.getCantidad() * p.getPrecio() + "<br />\n";
            precio = precio + p.getPrecio() * p.getCantidad();
        }
        //Se termina de escribir el mensaje
        mensaje = mensaje + "<p>-Precio total: $" + Integer.toString(precio) + "</p>\n"
                + "</blockquote>\n" + "\n" + "<p><br/>\n" + "Gracias por preferirnos,<br/>\n"
                + "Saludos,<br/>\n" + nomRest + "</p>";

        message.setContent(mensaje, "text/html");
        Transport.send(message);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (IOException | MessagingException | ServletException ex) {
            
            Logger.getLogger(Restaurantes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.net.MalformedURLException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, MalformedURLException {
        try {
            processRequest(request, response);
        } catch (IOException | MessagingException | ServletException ex) {
            Logger.getLogger(Restaurantes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
