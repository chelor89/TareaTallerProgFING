/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickorder.controllers;

import Fabrica.Fabrica;
import com.quickorder.model.Carrito;
import com.quickorder.model.EstadoSesion;
import dataTypes.DataCliente;
import dataTypes.DataRestaurante;
import interfaces.IControladorRestaurantes;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/casa")
/**
 *
 * @author Marcelo
 */
public class Home extends HttpServlet {

    Fabrica F = Fabrica.getInstance();

    public void initSession(HttpSession session) {
        if (session.getAttribute("carrito") == null) {
            session.setAttribute("carrito", new Carrito());
        } 
        
        if (session.getAttribute("estado_sesion") == null) {
            session.setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
        }
        if (session.getAttribute("controladorPedidos") == null) {
            session.setAttribute("controladorPedidos", F.getIControladorPedidos());
        }
        if (session.getAttribute("controladorClientes") == null) {
            session.setAttribute("controladorClientes", F.getIControladorClientes());
        }
        if (session.getAttribute("controladorRestaurantes") == null) {
            session.setAttribute("controladorRestaurantes", F.getIControladorRestaurantes());
        }
    }

    public static EstadoSesion getEstado(HttpServletRequest request) {
        return (EstadoSesion) request.getSession().getAttribute("estado_sesion");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        initSession(session);

        IControladorRestaurantes cr = (IControladorRestaurantes) session.getAttribute("controladorRestaurantes");
        LinkedList<String> resCats = new LinkedList<>();
        Set<String> cats = cr.listarCategorias();
        Set<DataRestaurante> restos = cr.listarRestaurantes();        

        resCats = ordenarCategorias(cr, cats);
        
        request.getSession()
                .setAttribute("restaurantes", restos);
        request.getSession()
                .setAttribute("10categorias", resCats);
        request.getSession()
                .setAttribute("categorias", cats);

        switch (getEstado(request)) {
            case NO_LOGIN:
                request.getRequestDispatcher("/WEB-INF/principal/paginaPrincipal.jsp").
                        forward(request, response);
                break;
            case LOGIN_CORRECTO:
                DataCliente usr = Login.getInfoClienteLogueado(request);
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

    public LinkedList<String> ordenarCategorias(IControladorRestaurantes cr, Set<String> cats) {
        int max = 0;
        String maxcat = "";
        LinkedList<String> resCats = new LinkedList<>();
        Set<String> aux = new HashSet<>();
        aux.addAll(cats);
        while (!aux.isEmpty()) {
            for (String cat : aux) {
                cr.seleccionarCategoria(cat);
                if (cr.listarRestaurantesCategoria().size() >= max) {
                    maxcat = cat;
                    max = cr.listarRestaurantesCategoria().size();
                }
            }
            aux.remove(maxcat);
            resCats.add(maxcat);
            max = 0;
        }
        return resCats;
    }
}
