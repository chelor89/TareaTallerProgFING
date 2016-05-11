/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickorder.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.java.dev.jaxb.array.StringArray;
import publicar.DataCliente;
import publicar.PublicadorImagenes;
import publicar.PublicadorImagenesService;
import publicar.PublicadorRestaurantes;
import publicar.PublicadorRestaurantesService;
import sun.misc.BASE64Decoder;

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

        String tipo = request.getParameter("tipo");
        String restaurante = request.getParameter("restaurante");
        String producto = request.getParameter("producto");
        String in = request.getParameter("indice");
        int indice = -1;
        if (in != null) {
            indice = Integer.valueOf(in);
        }

        URL url = new URL("http://" + (request.getSession().getAttribute("wsurl") + "/publicadorImagenes?wsdl"));

        PublicadorImagenesService service = new PublicadorImagenesService(url);
        PublicadorImagenes port = service.getPublicadorImagenesPort();

        String ruta = "/media/images/";
        String path = request.getServletContext().getRealPath("");
        String ruta_imagenes_estaticas = path + ruta;

        switch (tipo) {

            case "Perfil":
                DataCliente dc = Sesion.getInfoClienteLogueado(request);
                BufferedImage imgBuff = decodeToImage(port.getImagenPerfil(dc.getImagen()));
                if (imgBuff == null) {//poner imagen vacia
                    imgBuff = cargarImagen(ruta_imagenes_estaticas + "emptyProfile.png");
                }
                ImageIO.write(imgBuff, "png", response.getOutputStream());
                break;

            case "Producto":
                imgBuff = decodeToImage(port.getImagenProducto(restaurante, producto));
                if (imgBuff == null) {//poner imagen vacia
                    imgBuff = cargarImagen(ruta_imagenes_estaticas + "roast-beef-sandwich.jpg");
                }
                ImageIO.write(imgBuff, "png", response.getOutputStream());
                break;

            case "Restaurante":
                StringArray imgs = port.getImagenesRestaurante(restaurante);
                BufferedImage[] arrBuffImg = new BufferedImage[imgs.getItem().size()];
                int i = 0;
                for (String img : imgs.getItem()) {
                    arrBuffImg[i] = decodeToImage(img);
                    i++;
                }
                if (arrBuffImg.length == 0) {//poner imagen vacia
                    ImageIO.write(cargarImagen(ruta_imagenes_estaticas + "krusty_krab_cleanup.jpg"), "png", response.getOutputStream());
                } else {
                    ImageIO.write(arrBuffImg[indice], "png", response.getOutputStream());
                }

                break;
        }

    }

    public static BufferedImage decodeToImage(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return image;
    }

    public static BufferedImage cargarImagen(String ruta_imagen) {

        File imagen = new File(ruta_imagen);

        try {
            return ImageIO.read(imagen);
        } catch (IOException ex) {
            return null;
        }
    }

    ;

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
