/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejadores;

import entidades.RegistroAcceso;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Marcelo
 */
public class ManejadorRegistros {
    
    private LinkedList<RegistroAcceso> registros;
    
    private ManejadorRegistros() {       
        registros = new LinkedList<>();      
    }
    
    public static ManejadorRegistros getInstance() {
        return ManejadorRegistrosHolder.INSTANCE;
    }
    
    private static class ManejadorRegistrosHolder {

        private static final ManejadorRegistros INSTANCE = new ManejadorRegistros();
    }
    
    public void addRegistro(RegistroAcceso r){
        
        //Saca los de hace mas de 30 dias
        while (!registros.isEmpty() && r.getFecha().get(Calendar.YEAR) - 1 > registros.getFirst().getFecha().get(Calendar.YEAR)){
            registros.remove();
        }
        while (!registros.isEmpty() && ((r.getFecha().get(Calendar.DAY_OF_YEAR) - 30) % 365) > registros.getFirst().getFecha().get(Calendar.DAY_OF_YEAR)){
            registros.remove();
        }
            
        
        //Si se llega a los 10000 se saca el ultimo
        if (registros.size() == 10000)
            registros.remove();
        
        //Se agrega el nuevo registro
        registros.add(r);
    }
    
    public List<RegistroAcceso> getRegistros(){
        
        List<RegistroAcceso> ret = new ArrayList();
        
        for (RegistroAcceso R : registros)
            ret.add(R);
        
        return ret;
    }
}
