/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataTypes;

/**
 *
 * @author Marcelo
 */
public class DataRegistroAcceso {
    
    private String IP;
    private String URL;
    private String SO;
    private String navegador;
    
    public DataRegistroAcceso(String IP, String URL, String SO, String navegador){
        this.IP = IP;
        this.URL = URL;
        this.SO = SO;
        this.navegador = navegador;
    }
    
    public String getIP(){
        return this.IP;
    }
        
    public String getURL(){
        return this.URL;
    }
        
    public String getSO(){
        return this.SO;
    }
        
    public String getNavegador(){
        return this.navegador;
    }
}
