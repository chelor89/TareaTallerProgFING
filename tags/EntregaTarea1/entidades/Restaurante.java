package entidades;

import dataTypes.DataProducto;
import dataTypes.DataRestaurante;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Restaurante extends Usuario{
 
    private Set<String> imagenes;
    private Map<String, Individual> individuales;
    private Map<String, Promocion> promociones;
    private Map<String, Categoria> categorias;

     public Restaurante(String nick, String nom, String dir, String mail, Set<String> imgs) {
        super(nick, nom, dir, mail);
        promociones = new HashMap<>();
        individuales = new HashMap<>();
        categorias = new HashMap<>();
        imagenes = imgs;
    }
     
    public void crearIndividual(String nombre, String descripcion, int precio, String imagen){
        Individual I = new Individual(nombre, descripcion, precio, this, imagen);
        individuales.put(nombre,I);
    }
   
    public void crearPromocion(String nombre, String descripcion, int descuento, int precio, String imagen){
        
        Promocion P = new Promocion(nombre, descripcion ,imagen, descuento, this);
        promociones.put(nombre,P);
    }
    
//    public Set<String> getImagenes(){
//        return imagenes;                
//    } 
    
    public Map<String, Individual> getIndividuales(){
        return individuales;                
    }
    
    public Map<String, Promocion> getPromociones(){
        return promociones;                
    }
    
//    public Map<String, Categoria> getCategorias(){
//        return categorias;
//    }
     
    public boolean existeProducto(String key){
        return (promociones.containsKey(key) || individuales.containsKey(key));
    }
    
    public boolean existeIndividual(String key){
        return (individuales.containsKey(key));
    }
    
 /*   public boolean existePromocion(String key){
        return (promociones.containsKey(key));
    }
    
    // agrego la promocion al conjunto de promociones del restaurant
    public void agregarProducto(Promocion promo){
        promociones.put(promo.getNombre(), promo);
    }
    
    // agrego el individual al conjunto de individuales del restaurant
    public void agregarProducto(Individual ind){
        individuales.put(ind.getNombre(), ind);
    }*/
    
    public void agregarCategoria(Categoria cat){
        categorias.put(cat.getNombre(), cat);
    }
    
    public void agregarIndividualAPromocion(String nombreInd, int cantidad, Promocion promo){
        Individual ind;
        ind = individuales.get(nombreInd);
        CantInd CI = new CantInd(cantidad, ind,promo);
        ind.agregarPromoAInd(CI);
        promo.agregarIndAPromo(CI);
    }
    
    public Promocion getPromocion(String key){
        return promociones.get(key);
    }
    public Individual getIndividual(String key){
        return individuales.get(key);
    }
    
    // obtener dataTypes de categorias
   /* public Set<String> listarCategorias(){        
        Set<String> sc = new HashSet<>(); 
        
        for(Map.Entry<String, Categoria> entry : categorias.entrySet()) {
            String k = entry.getKey();
            Categoria v = entry.getValue();
            
            String c = v.getNombre();
            sc.add(c);
        }       
        return sc;        
    }*/
    
    // obtener dataTypes de productos
    public Set<DataProducto> listarProductos(int tipo){        
        Set<DataProducto> sdp = new HashSet<>(); 
        if (tipo == 1 || tipo == 3)
            for(Entry<String, Individual> entry : individuales.entrySet()) {
                String k = entry.getKey();
                Producto v = entry.getValue();
                DataProducto dp = new DataProducto(v.getNombre(),v.getDescripcion(), v.getPrecio(),v.getTipo(),
                        v.getRestaurante().getNombre(),v.getRestaurante().getNickName(),v.getRestaurante().getMail(),
                        v.getImagen(),false, -1, -1 , -1); 
                sdp.add(dp);
            }
        if (tipo == 2 || tipo == 3)
            for(Entry<String, Promocion> entry : promociones.entrySet()) {
                String k = entry.getKey();
                Producto v = entry.getValue();

                DataProducto dp = new DataProducto(v.getNombre(),v.getDescripcion(), v.getPrecio(),v.getTipo(),
                        v.getRestaurante().getNombre(),v.getRestaurante().getNickName(),v.getRestaurante().getMail(),
                        v.getImagen(),v.getActiva(), -1, -1 , -1);
                sdp.add(dp);
            }       
        return sdp;        
    }
    
    public DataRestaurante getInfo(){
        
        return(new DataRestaurante(nombre, nickname, mail, direccion, imagenes, this.listarProductos(3) ));
        
    }
    
}
