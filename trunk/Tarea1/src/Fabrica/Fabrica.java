package Fabrica;

import controladores.ControladorClientes;
import controladores.ControladorPedidos;
import controladores.ControladorRestaurantes;
import interfaces.IControladorClientes;
import interfaces.IControladorPedidos;
import interfaces.IControladorRestaurantes;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marcelo
 */
public class Fabrica {
    
    private static final Fabrica INSTANCE = new Fabrica();
    
    private Fabrica(){};
    
    public static Fabrica getInstance() {
        return Fabrica.INSTANCE;
    }
    
    public IControladorClientes getIControladorClientes(){
        return new ControladorClientes();
    }
       
    public IControladorPedidos getIControladorPedidos(){
        return new ControladorPedidos();
    }
      
    public IControladorRestaurantes getIControladorRestaurantes(){
        return new ControladorRestaurantes();
    }

}
