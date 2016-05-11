/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickorder.controllers;

import com.quickorder.model.Carrito;
import com.quickorder.model.ProductoPedido;
import dataTypes.DataCliente;
import dataTypes.DataProducto;
import dataTypes.DataRestaurante;
import dataTypes.Estado;
import interfaces.IControladorPedidos;
import interfaces.IControladorRestaurantes;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain");
            
            String tipo = request.getParameter("tipo");
            String producto = request.getParameter("producto");
            String restaurante = request.getParameter("restaurante");
            String cantidad = request.getParameter("cantidad");
            
            HttpSession objSesion = request.getSession();
            IControladorPedidos cp = (IControladorPedidos) objSesion.getAttribute("controladorPedidos");
            IControladorRestaurantes cr = (IControladorRestaurantes) objSesion.getAttribute("controladorRestaurantes");
            
            switch(tipo){
                
                case "pagRestaurante": 
                    cr.seleccionarRestaurante(restaurante);
                    DataRestaurante dr = cr.verInfoRestauranteM();
                    request.setAttribute("restaurante", dr);
                    request.getRequestDispatcher("/WEB-INF/restaurante/detalles.jsp").forward(request, response);
                    break;
                    
                case "getProducto":
                        cr.seleccionarProductoDeRestaurante(producto);
                        DataProducto dp = cr.verInfoProducto();
                        ProductoPedido pp = new ProductoPedido(dp,0);
                        response.getWriter().write(pp.toJsonText());
                    break;
                    
                case "addProducto":
                    dr = cr.verInfoRestauranteM();
                    Carrito C = (Carrito) request.getSession().getAttribute("carrito");
                    cr.seleccionarProductoDeRestaurante(producto);
                    dp = cr.verInfoProducto();
                    pp = new ProductoPedido(dp,Integer.valueOf(cantidad));
                    //Si se cambia de restaurante se borra el pedido en curso y se inicia de nuevo
                    if (!dr.getNick().equals(C.getRestaurante()))
                        C.clear();
                    //Si el restaurante no esta seteado se setea en el actual
                    if (C.getRestaurante().equals(""))
                        C.setRestaurante(dr.getNick());
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
                    DataCliente cliente = Login.getInfoClienteLogueado(request);
                    cp.SeleccionarCliente(cliente.getNick());
                    cp.altaPedido();
                    cp.SeleccionarRestaurante(C.getRestaurante());
                    cp.AgregarPedidoACliente();
                    for (ProductoPedido prp : C.getProductos()){
                        cp.SeleccionarProducto(prp.getNombre());
                        cp.agregarProducto(prp.getCantidad());
                    }
                    cp.actualizarEstadoPedido(Estado.Preparacion);
                    C.clear();
                    break;
            }
            
            
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
