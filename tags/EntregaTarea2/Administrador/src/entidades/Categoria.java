package entidades;

import dataTypes.DataRestaurante;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Categoria {
  
    private String nombre;
    
    private Map<String, Restaurante> restaurantes;
    
    public Categoria(String nombre){
        this.nombre = nombre;
        restaurantes = new HashMap<>();
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void agregarRestaurante(Restaurante res){
        restaurantes.put(res.getNombre(), res);
    }
    
//    public Map<String, Restaurante> getRestaurantes(){
//        return restaurantes;
//    }
    
    // obtener dataTypes de restaurantes   
    public Set<DataRestaurante> listarRestaurantes() throws IOException{   
        Set<DataRestaurante> sdr = new HashSet<>(); 
        
        for(Entry<String, Restaurante> entry : restaurantes.entrySet()) {
            String k = entry.getKey();
            Restaurante v = entry.getValue();
            
            DataRestaurante dr = v.getInfo();
            // DataRestaurante dr = new DataRestaurante(v.getNombre(), v.getNickName()); 
            sdr.add(dr);
        }       
        return sdr; 
    }       
  
}
