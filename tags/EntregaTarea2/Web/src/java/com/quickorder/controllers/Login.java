/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.quickorder.controllers;

import Fabrica.Fabrica;
import com.quickorder.exceptions.ClienteNoEncontrado;
import com.quickorder.model.EstadoSesion;
import static com.quickorder.model.EstadoSesion.LOGIN_CORRECTO;
import dataTypes.DataCliente;
import interfaces.IControladorClientes;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {
  
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession objSesion = request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        EstadoSesion es = (EstadoSesion) objSesion.getAttribute("estado_sesion");
        //chequea contraseña
        IControladorClientes CC = (IControladorClientes) objSesion.getAttribute("controladorClientes");
        if (es != LOGIN_CORRECTO){
            if (login != null && password != null){
                if (CC.seleccionarCliente(login)){
                        DataCliente cli = CC.verInfoCliente();
                        if(!cli.getContra().equals(password)){
                                request.setAttribute("mensaje", "C");
                                request.getRequestDispatcher("/WEB-INF/usuario/inicioSesion.jsp").forward(request, response);
                        }
                        else{
                                // setea el usuario logueado
                                objSesion.setAttribute("usuario_logueado", cli.getMail());
                                objSesion.setAttribute("estado_sesion", LOGIN_CORRECTO);
                                request.getRequestDispatcher("/casa").forward(request, response);
                        }
                } else{
                        request.setAttribute("mensaje", "U");
                        request.getRequestDispatcher("/WEB-INF/usuario/inicioSesion.jsp").forward(request, response);
                }
            }else request.getRequestDispatcher("/WEB-INF/usuario/inicioSesion.jsp").forward(request, response);
        }else request.getRequestDispatcher("/casa").forward(request, response);
    }
	
    static public DataCliente getInfoClienteLogueado(HttpServletRequest request){
        IControladorClientes CC = (IControladorClientes) request.getSession().getAttribute("controladorClientes");
        String usuario = (String) request.getSession().getAttribute("usuario_logueado");
        if (usuario != null){
            CC.seleccionarCliente(usuario);
            return CC.verInfoCliente();
        }else return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
