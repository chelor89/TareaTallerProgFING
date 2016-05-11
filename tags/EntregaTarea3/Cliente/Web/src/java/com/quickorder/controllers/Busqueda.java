/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickorder.controllers;

import publicar.DataRestaurante;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import publicar.PublicadorBusqueda;
import publicar.PublicadorBusquedaService;
import publicar.PublicadorRestaurantes;
import publicar.PublicadorRestaurantesService;

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

       URL url = new URL("http://" + (request.getSession().getAttribute("wsurl") + "/publicadorBusqueda?wsdl"));
      
        PublicadorBusquedaService service = new PublicadorBusquedaService(url);
        PublicadorBusqueda port = service.getPublicadorBusquedaPort();
       List<DataRestaurante> resRestos;
        
        String categoria = request.getParameter("categoria");

        if (request.getParameter("tipo").equals("busqueda")) {
            String busqueda = (request.getParameter("restaurante"));        
            resRestos = port.buscar(busqueda).getItem();
        } else {
            resRestos = port.listarPorCategoria(categoria).getItem();
        }

        if (request.getParameter("orden") != null) {

            if (request.getParameter("orden").equals("alfabeticamente")) {
                resRestos = ordenarRestaurantesAlf(resRestos);
            } else {
                resRestos = ordenarRestaurantesPuntaje(resRestos);
            }
        } 
        
        request.getSession().setAttribute("restaurantes", resRestos);
     

        request.getRequestDispatcher("/WEB-INF/restaurante/busqueda.jsp").forward(request, response);
    }


    public LinkedList<DataRestaurante> ordenarRestaurantesAlf(List<DataRestaurante> rr) {
        Map<String, DataRestaurante> mapRestos = new HashMap<>();
        for (DataRestaurante dr : rr) {
            mapRestos.put(dr.getNick(), dr);
        }
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

    public LinkedList<DataRestaurante> ordenarRestaurantesPuntaje(List<DataRestaurante> rr) {
        Map<String, DataRestaurante> mapRestos = new HashMap<>();
        for (DataRestaurante dr : rr) {
            mapRestos.put(dr.getNick(), dr);
        }
        DataRestaurante res;
        DataRestaurante maxres = null;
        float maxPts = -1;
        LinkedList<DataRestaurante> resRestos = new LinkedList<>();
        while (!mapRestos.isEmpty()) {
            for (Entry e : mapRestos.entrySet()) {
                res = (DataRestaurante) e.getValue();
                if (res.getPuntaje() > maxPts) {
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
