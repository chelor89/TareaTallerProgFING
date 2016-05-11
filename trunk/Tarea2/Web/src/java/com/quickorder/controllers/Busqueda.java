/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickorder.controllers;

import dataTypes.DataProducto;
import dataTypes.DataRestaurante;
import interfaces.IControladorRestaurantes;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cyro.rodriguez
 */
public class Busqueda extends HttpServlet {

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

        response.setContentType("text/html;charset=UTF-8");
        HttpSession objSesion = request.getSession();
        IControladorRestaurantes cr = (IControladorRestaurantes) objSesion.getAttribute("controladorRestaurantes");
        LinkedList<DataRestaurante> resRestos = new LinkedList<>();
        LinkedList<String> resCats = new LinkedList<>();
        Set<String> cats = cr.listarCategorias();
        Set<DataRestaurante> restos = cr.listarRestaurantes();
        Map<String, DataRestaurante> mapRestos = new HashMap<>();
    
        if (request.getParameter("tipo").equals("busqueda")) {
            String busqueda = (request.getParameter("restaurante"));
            for (String cat : cats) {
                if (cat.toLowerCase().contains(busqueda.toLowerCase())) {
                    cr.seleccionarCategoria(cat);
                    for (DataRestaurante dr : cr.listarRestaurantesCategoria()) {
                        if(!mapRestos.containsKey(dr.getNick()))
                            mapRestos.put(dr.getNick(), dr);
                    }
                }
            }
            for (DataRestaurante dr : restos) {
                if (dr.getNombre().toLowerCase().contains(busqueda.toLowerCase())) {
                     if(!mapRestos.containsKey(dr.getNick()))
                            mapRestos.put(dr.getNick(), dr);
                }
            }
            for (DataProducto dp : cr.listarProductos()){
                if (dp.getNombre().toLowerCase().contains(busqueda.toLowerCase())){
                    cr.seleccionarRestaurante(dp.getNickRes());
                    if(!mapRestos.containsKey(dp.getNickRes()))
                    mapRestos.put(dp.getNickRes(), cr.verInfoRestauranteM());
                }
            }
            resRestos.addAll(mapRestos.values());
        } else {
            cr.seleccionarCategoria((String) request.getParameter("categoria"));
            resRestos.addAll(cr.listarRestaurantesCategoria());
            for (DataRestaurante dr : cr.listarRestaurantesCategoria()) {
                mapRestos.put(dr.getNick(), dr);
            }
        }

        if (request.getParameter("orden") != null) {

            if (request.getParameter("orden").equals("alfabeticamente")) {
                resRestos = oredenarRestaurantesAlf(mapRestos);
            } else {
                resRestos = ordenarRestaurantesPuntaje(mapRestos);
            }
        }

        resCats = ordenarCategorias(cr, cats);

        request.getSession().setAttribute("restaurantes", resRestos);
        request.getSession().setAttribute("10categorias", resCats);
        request.getSession().setAttribute("categorias", cats);

        request.getRequestDispatcher("/WEB-INF/restaurante/busqueda.jsp").forward(request, response);
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

    public LinkedList<DataRestaurante> oredenarRestaurantesAlf(Map<String, DataRestaurante> mapRestos) {
        DataRestaurante res;
        DataRestaurante maxres = null;
        String maxName = "ZZZZZZZZZZZZZZ";
        LinkedList<DataRestaurante> resRestos = new LinkedList<>();
        while (!mapRestos.isEmpty()) {
            for (Entry e : mapRestos.entrySet()) {
                res = (DataRestaurante) e.getValue();
                if (res.getNombre().compareToIgnoreCase(maxName) <= 0) {
                    maxres = res;
                    maxName = res.getNombre();
                }
            }
            mapRestos.remove(maxres.getNick());
            resRestos.add(maxres);
            maxName = "ZZZZZZZZZZZZZZ";
        }
        return resRestos;
    }

    public LinkedList<DataRestaurante> ordenarRestaurantesPuntaje(Map<String, DataRestaurante> mapRestos) {
        DataRestaurante res;
        DataRestaurante maxres = null;
        float maxPts = -1;
        LinkedList<DataRestaurante> resRestos = new LinkedList<>();
        while (!mapRestos.isEmpty()) {
            for (Entry e : mapRestos.entrySet()) {
                res = (DataRestaurante) e.getValue();
                if (res.getPuntaje() > maxPts){
                    maxres = res;
                    maxPts = res.getPuntaje();
                }
            }
            mapRestos.remove(maxres.getNick());
            resRestos.add(maxres);
            maxPts = -1;
        }
        return resRestos;
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
