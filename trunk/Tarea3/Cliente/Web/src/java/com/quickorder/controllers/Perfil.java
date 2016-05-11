package com.quickorder.controllers;

import static com.quickorder.model.EstadoSesion.LOGIN_CORRECTO;
import java.io.IOException;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import publicar.DataProducto;
import publicar.DataProductoArray;
import publicar.PublicadorPerfil;
import publicar.PublicadorPerfilService;

/**
 *
 * @author Marcelo
 */
public class Perfil extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession objSesion = request.getSession();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        URL url = new URL("http://" + (request.getSession().getAttribute("wsurl") + "/publicadorPerfil?wsdl"));
        PublicadorPerfilService service = new PublicadorPerfilService(url);
        PublicadorPerfil port = service.getPublicadorPerfilPort();

        String tipo = request.getParameter("tipo");
        String np = request.getParameter("numPedido");
        String indice = request.getParameter("indice");
        int numeroPedido = 0;
        if (np != null) {
            numeroPedido = Integer.valueOf(np);
        }

        switch (tipo) {
            case "comentario":
                String numeroId = request.getParameter("numeroId");
                String puntaje = request.getParameter("estrellitaestrellitadondeestas");
                String textoComentario = request.getParameter("comentario");
                port.agregarComentario(Integer.valueOf(numeroId), textoComentario, Integer.valueOf(puntaje));
                request.getRequestDispatcher("/WEB-INF/cliente/verPerfil.jsp").forward(request, response);
                break;
            case "verPerfil":
                if (request.getSession().getAttribute("estado_sesion").equals(LOGIN_CORRECTO)) {
                    request.getRequestDispatcher("/WEB-INF/cliente/verPerfil.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/WEB-INF/usuario/inicioSesion.jsp").forward(request, response);
                }
                break;
            case "obtenerCantidadProdsPed":
                int cant = port.obtenerCantidad(numeroPedido);
                response.getWriter().write(String.valueOf(cant));
                break;
            case "obtenerDatosProd":
                cant = port.obtenerCantidad(numeroPedido);

                String numeroAnteriorPedido = "0";
                if (objSesion.getAttribute("numAnteriorPedido") != null) {
                    numeroAnteriorPedido = String.valueOf(objSesion.getAttribute("numAnteriorPedido"));
                }

                if (objSesion.getAttribute("arreglo") == null || !numeroAnteriorPedido.equals(np)) {
                    DataProductoArray dpArray = port.getProductos(numeroPedido, cant);
                    objSesion.setAttribute("arreglo", dpArray);
                    objSesion.setAttribute("numAnteriorPedido", numeroPedido);
                }

                DataProductoArray dpaA = (DataProductoArray) objSesion.getAttribute("arreglo");
                DataProducto[] dpa = dpaA.getItem().toArray(new DataProducto[dpaA.getItem().size()]);
                String t;

                if (dpa[Integer.valueOf(indice)].getTipo() == 0) {
                    t = "Individual";
                } else {
                    t = "Promocion";
                }

                response.getWriter().write(dpa[Integer.valueOf(indice)].getNombre() + "^#^"
                        + t + "^#^"
                        + "$" + dpa[Integer.valueOf(indice)].getPrecio() + "^#^"
                        + dpa[Integer.valueOf(indice)].getCantidad() + "^#^"
                        + "$" + dpa[Integer.valueOf(indice)].getCantidad() * dpa[Integer.valueOf(indice)].getPrecio());
                break;
            case "obtenerPrecioPedido":
                int precio = port.getPrecioTotal(numeroPedido);
                response.getWriter().write("$" + String.valueOf(precio));
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
