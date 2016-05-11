package manejadores;

import dataTypes.DataRestaurante;
import entidades.Categoria;
import entidades.Restaurante;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ManejadorRestaurantes {
    
    private Map<String, Restaurante> restaurantes;  
    private Map<String, Restaurante> restaurantespormail;  
    private Map<String, Categoria> categorias;
    
    private ManejadorRestaurantes() {
        restaurantes = new HashMap<>();
        restaurantespormail = new HashMap<>();
        categorias = new HashMap<>();
    }
    
    public static ManejadorRestaurantes getInstance() {
        return ManejadorRestaurantesHolder.INSTANCE;
    }
    
    private static class ManejadorRestaurantesHolder {

        private static final ManejadorRestaurantes INSTANCE = new ManejadorRestaurantes();
    }
    
    public void agregarRestaurante(Restaurante r){
        restaurantes.put(r.getNickName(), r);   
        restaurantespormail.put(r.getMail(), r);    
    }
    
    public void borrarRestaurante(Restaurante r){
        restaurantes.remove(r.getNickName());
         restaurantespormail.remove(r.getMail()); 
    }
    
    public Restaurante getRestaurante(String key){
        Restaurante aux = restaurantes.get(key);
        if (aux == null)
            aux = restaurantespormail.get(key);
        return aux;
    }
    
    public boolean existeRestaurant(String key, String mail){
        return restaurantes.containsKey(key) ||  restaurantespormail.containsKey(mail);
    }
    
    public boolean existeRestaurantPorNick(String key){
        return restaurantes.containsKey(key);
    }
    
        // obtener restaurantes   
    public Set<DataRestaurante> listarRestaurantes(){   
        Set<DataRestaurante> sdr = new HashSet<>(); 
        
        for(Map.Entry<String, Restaurante> entry : restaurantes.entrySet()) {
            String k = entry.getKey();
            Restaurante v = entry.getValue();
            
            DataRestaurante dr = new DataRestaurante(v.getNombre(), v.getNickName()); 
            sdr.add(dr);
        }       
        return sdr; 
    }  
    
    public void agregarCategoria(Categoria c){
        categorias.put(c.getNombre(), c);        
    }
    
    public void borrarCategoria(Categoria r){
        categorias.remove(r.getNombre());
    }
    
    public Categoria getCategoria(String key){
        return categorias.get(key);
    }
    
    public boolean existeCategoria(String key){
        return categorias.containsKey(key);
    }
    
    // obtener categorias
    public Set<String> listarCategorias(){        
        Set<String> sc = new HashSet<>(); 
        
        for(Map.Entry<String, Categoria> entry : categorias.entrySet()) {
            String k = entry.getKey();
            Categoria v = entry.getValue();
            
            String c = v.getNombre();
            sc.add(c);
        }       
        return sc;        
    }
    
    public Map<String, Restaurante> getRestaurantes(){
        return restaurantes;
    }
    
    public Map<String, Categoria> getCategorias(){
        return categorias;
    }
}
