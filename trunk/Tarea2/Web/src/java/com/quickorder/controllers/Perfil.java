package com.quickorder.controllers;

import static com.quickorder.model.EstadoSesion.LOGIN_CORRECTO;
import dataTypes.DataCliente;
import dataTypes.DataPedido;
import dataTypes.DataProducto;
import entidades.Producto;
import interfaces.IControladorPedidos;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marcelo
 */
public class Perfil extends HttpServlet {
    
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            HttpSession objSesion = request.getSession();
            IControladorPedidos cp = (IControladorPedidos) objSesion.getAttribute("controladorPedidos");
            
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain");
            
            String tipo = request.getParameter("tipo");
            String numeroPedido = request.getParameter("numPedido");
            String indice = request.getParameter("indice");
            String totalP = request.getParameter("totalPrecio");
            
            switch (tipo) {
                case "comentario":
                    String numeroId = request.getParameter("numeroId");
                    String puntaje = request.getParameter("estrellitaestrellitadondeestas");
                    String textoComentario = request.getParameter("comentario");
                    cp.seleccionarPedido(Integer.valueOf(numeroId));
                    cp.AgregarComentarioAPedido(textoComentario, Integer.valueOf(puntaje));
                    request.getRequestDispatcher("/WEB-INF/cliente/verPerfil.jsp").forward(request, response);
                    break;
                case "verPerfil":
                    if (request.getSession().getAttribute("estado_sesion").equals(LOGIN_CORRECTO))
                        request.getRequestDispatcher("/WEB-INF/cliente/verPerfil.jsp").forward(request, response);
                    else request.getRequestDispatcher("/WEB-INF/usuario/inicioSesion.jsp").forward(request, response);
                    break;
                case "obtenerCantidadProdsPed":
                    cp.seleccionarPedido(Integer.valueOf(numeroPedido));
                    DataPedido dp = cp.verInfoPedido();
                    int cant = dp.getProductos().size();
                    response.getWriter().write(String.valueOf(cant));
                    break;
                case "obtenerDatosProd":
                    cp.seleccionarPedido(Integer.valueOf(numeroPedido));
                    dp = cp.verInfoPedido();
                    cant = dp.getProductos().size();

                    String numeroAnteriorPedido = (String) objSesion.getAttribute("numAnteriorPedido");

                    if(objSesion.getAttribute("arreglo") == null || !numeroAnteriorPedido.equals(numeroPedido)){
                        DataProducto[] dpArray = dp.getProductos().toArray(new DataProducto[cant]);
                        objSesion.setAttribute("arreglo", dpArray);
                        objSesion.setAttribute("numAnteriorPedido", numeroPedido);
                    }

                    DataProducto[] dpa = (DataProducto[]) objSesion.getAttribute("arreglo");
                    String t;

                    if(dpa[Integer.valueOf(indice)].getTipo() == Producto.Individual) t = "Individual"; 
                    else t = "Promocion";

                    response.getWriter().write(dpa[Integer.valueOf(indice)].getNombre() + "^#^" +
                            t +"^#^"+
                            "$" + dpa[Integer.valueOf(indice)].getPrecioUnitario()+"^#^" + 
                            dpa[Integer.valueOf(indice)].getCantidad() + "^#^" + 
                            "$"+dpa[Integer.valueOf(indice)].getCantidad()*dpa[Integer.valueOf(indice)].getPrecioUnitario());
                    break;
                case "obtenerPrecioPedido":
                    cp.seleccionarPedido(Integer.valueOf(numeroPedido));
                    dp = cp.verInfoPedido();
                    response.getWriter().write("$" + String.valueOf(dp.getPrecioTotal()));
                    break;
            }

	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/** 
	 * Handles the HTTP <code>GET</code> method.
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
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
