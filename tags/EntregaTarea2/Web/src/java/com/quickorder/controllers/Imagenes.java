/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickorder.controllers;

import dataTypes.DataCliente;
import dataTypes.DataRestaurante;
import interfaces.IControladorRestaurantes;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marcelo
 */
public class Imagenes extends HttpServlet {

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
        response.setContentType("image/png");
        IControladorRestaurantes cr = (IControladorRestaurantes) request.getSession().getAttribute("controladorRestaurantes");
        
        String tipo = request.getParameter("tipo");
        String restaurante = request.getParameter("restaurante");
        String producto = request.getParameter("producto");
        String in = request.getParameter("indice");
        int indice = -1;
        if (in != null)
            indice = Integer.valueOf(in);
        
        
        
        String ruta = "\\media\\images\\";
        String path = request.getServletContext().getRealPath("");
        String ruta_imagenes_estaticas = path + ruta;

        
        switch (tipo){
            
            case "Perfil":
                DataCliente dc = Login.getInfoClienteLogueado(request);
                if (dc.getImagen() != null){
                    BufferedImage imgBuff = dc.getImagen();
                    ImageIO.write(imgBuff, "png", response.getOutputStream());
                }else{
                    BufferedImage imgBuff = cargarImagen(ruta_imagenes_estaticas + "emptyProfile.png");
                    ImageIO.write(imgBuff, "png", response.getOutputStream());
                }
                break;
                
            case "Producto":
                cr.seleccionarRestaurante(restaurante);
                cr.seleccionarProductoDeRestaurante(producto);
                if (cr.verInfoProducto().getImagen() != null){
                    BufferedImage imgBuff = cr.verInfoProducto().getImagen();
                    ImageIO.write(imgBuff, "png", response.getOutputStream());
                }else{
                    BufferedImage imgBuff = cargarImagen(ruta_imagenes_estaticas + "roast-beef-sandwich.jpg");
                    ImageIO.write(imgBuff, "png", response.getOutputStream());
                }//poner imagen vacia
                break;
                
            case "Restaurante":
                cr.seleccionarRestaurante(restaurante);
                DataRestaurante dr = cr.verInfoRestauranteM();
                BufferedImage[] arrImg = dr.getImagenes();
                if (arrImg.length != 0) {
                    ImageIO.write(arrImg[indice], "png", response.getOutputStream());
                }else{
                    BufferedImage imgBuff = cargarImagen(ruta_imagenes_estaticas + "krusty_krab_cleanup.jpg");
                    ImageIO.write(imgBuff, "png", response.getOutputStream());
                }//poner imagen vacia
                break;
        }
        
    }
    
    public static BufferedImage cargarImagen(String ruta_imagen){

        File imagen = new File( ruta_imagen);
        
        try {
            return ImageIO.read(imagen);
        } catch (IOException ex) {
            return null;
        }
    };

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
