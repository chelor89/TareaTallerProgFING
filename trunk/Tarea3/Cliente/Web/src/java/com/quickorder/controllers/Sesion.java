/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickorder.controllers;

import com.quickorder.model.EstadoSesion;
import static com.quickorder.model.EstadoSesion.LOGIN_CORRECTO;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jasypt.util.password.StrongPasswordEncryptor;
import publicar.DataCliente;
import publicar.PublicadorPerfil;
import publicar.PublicadorPerfilService;

public class Sesion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        URL url = new URL("http://" + (request.getSession().getAttribute("wsurl") + "/publicadorPerfil?wsdl"));
        PublicadorPerfilService service = new PublicadorPerfilService(url);
        PublicadorPerfil port = service.getPublicadorPerfilPort();
        
        HttpSession objSesion = request.getSession();
        String tipo = request.getParameter("tipo");

        if ("cerrar".equals(tipo)) {
            objSesion.setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
            objSesion.setAttribute("usuario_logueado", null);
            request.getRequestDispatcher("/casa").forward(request, response);
        } else {
            String nick = request.getParameter("nick");
            String password = request.getParameter("password");
            EstadoSesion es = (EstadoSesion) objSesion.getAttribute("estado_sesion");
            if (es != LOGIN_CORRECTO) {
                if (nick != null && password != null) {
                    //chequea usuario
                    if (port.existeCliente(nick)) {
                        DataCliente cli = port.getCliente(nick);
                        //chequea contraseña
                        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
                        if (passwordEncryptor.checkPassword(password,cli.getContrasenia())) {
                            // correct login!
                            // setea el usuario logueado
                            objSesion.setAttribute("usuario_logueado", cli.getMail());
                            objSesion.setAttribute("estado_sesion", LOGIN_CORRECTO);
                            request.getRequestDispatcher("/casa").forward(request, response);
                          } else {
                            // bad login!
                            request.setAttribute("mensaje", "C");
                            request.getRequestDispatcher("/WEB-INF/usuario/inicioSesion.jsp").forward(request, response);
                          }
                    } else {
                        request.setAttribute("mensaje", "U");
                        request.getRequestDispatcher("/WEB-INF/usuario/inicioSesion.jsp").forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("/WEB-INF/usuario/inicioSesion.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("/casa").forward(request, response);
            }
        }
    }

    static public DataCliente getInfoClienteLogueado(HttpServletRequest request) {
        try {
            URL url = new URL("http://" + (request.getSession().getAttribute("wsurl") + "/publicadorPerfil?wsdl"));
            PublicadorPerfilService service = new PublicadorPerfilService(url);
            PublicadorPerfil port = service.getPublicadorPerfilPort();
            String usuario = (String) request.getSession().getAttribute("usuario_logueado");
            if (usuario != null) {
                DataCliente cli = port.getCliente(usuario);
                return cli;
            } else {
                return null;
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Sesion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
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
