/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickorder.controllers;

import com.quickorder.model.Carrito;
import com.quickorder.model.EstadoSesion;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import publicar.DataCliente;
import publicar.DataRestaurante;
import publicar.DataRestauranteArray;
import publicar.PublicadorBusqueda;
import publicar.PublicadorBusquedaService;

//@WebServlet("/casa")
/**
 *
 * @author Marcelo
 */
public class Home extends HttpServlet {

    public void initSession(HttpSession session) {    
     
        if (session.getAttribute("carrito") == null) {
            session.setAttribute("carrito", new Carrito());
        }

        if (session.getAttribute("estado_sesion") == null) {
            session.setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
        }

    }

    public static EstadoSesion getEstado(HttpServletRequest request) {
        return (EstadoSesion) request.getSession().getAttribute("estado_sesion");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        initSession(session);

      URL url = new URL("http://" + (request.getSession().getAttribute("wsurl") + "/publicadorBusqueda?wsdl"));
      
        PublicadorBusquedaService service = new PublicadorBusquedaService(url);
        PublicadorBusqueda port = service.getPublicadorBusquedaPort();

        //   List<String> resCats = port.getCategorias(true).getCats();
        //  List<String> cats = port.getCategorias(false).getCats(); 
        DataRestauranteArray restosa = port.buscar("");
        List<DataRestaurante> restos = restosa.getItem();
        request.getSession().setAttribute("restaurantes", restos);

        //  request.getSession().setAttribute("10categorias", resCats);
        // request.getSession().setAttribute("categorias", cats);
        switch (getEstado(request)) {
            case NO_LOGIN:
                request.getRequestDispatcher("/WEB-INF/principal/paginaPrincipal.jsp").
                        forward(request, response);
                break;
            case LOGIN_CORRECTO:
                DataCliente usr = Sesion.getInfoClienteLogueado(request);
                request.setAttribute("usuario", usr);
                request.getRequestDispatcher("/WEB-INF/principal/paginaPrincipal.jsp").forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

}
