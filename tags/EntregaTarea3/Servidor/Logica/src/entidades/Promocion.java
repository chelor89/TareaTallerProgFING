package entidades;

import dataTypes.DataCantInd;
import dataTypes.DataPedido;
import dataTypes.DataProducto;
import static entidades.ImagenesServidor.escribirImagen;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Promocion extends Producto {
 
    private boolean activa;
    private int descuento;
    
    private Map<String, CantInd> individuales;
    
    
    // constructor con imagen
    public Promocion(String nom, String descripcion, BufferedImage img, int desc, Restaurante r) throws IOException{
        super(nom, descripcion, r);
        
        String url = guardarImagen(img,nom,restaurante);        
        this.imagen = url;
        this.precio = 0;
        this.tipo = Promocion;
        this.descuento = desc;
        activa = true;        
        individuales = new HashMap<>();
    }
    
    public Map<String, CantInd> getIndividuales(){
        return individuales;
    }
    
    @Override
    public boolean getActiva(){
        return activa;
    }
    
    @Override
    public int getDescuento(){
        return descuento;
    }
    
@Override
    public void setImagen(BufferedImage im){
        try {
            this.imagen = guardarImagen(im,this.getNombre(),this.getRestaurante());
        } catch (IOException ex) {
            Logger.getLogger(Individual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void setActiva(boolean v){
        this.activa = v;
    }
    
    @Override
    public void setDescuento(int desc){
        this.descuento = desc;
    }
    
    // agrego el individual al conjunto de indivuales que conforman la promocion
    public void agregarIndAPromo(CantInd CI){
        float  p = 1 -((float) this.descuento) / 100;                
        this.precio = (int) (this.precio + (CI.getCantidad() * CI.getIndividual().getPrecio() * p));
        individuales.put(CI.getIndividual().getNombre(), CI);
    } 
    
//    public CantInd obtenerIndividual(String key){
//        return individuales.get(key);
//    }
    
    // obtener dataTypes de individuales dentro de promocion
//    public Set<DataProducto> listarIndividuales(){        
//        Set<DataProducto> sdp = new HashSet<>(); 
//        
//        for(Map.Entry<String, CantInd> entry : individuales.entrySet()) {
//            String k = entry.getKey();
//            CantInd v = entry.getValue();
//            
//            Producto p = v.getPromocion();
//            Restaurante r = p.getRestaurante();
//               
//            DataProducto dp = new DataProducto(p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getTipo(), 
//                    r.getNombre(), r.getNickName(), r.getMail(), p.getImagen(), p.getActiva(),  p.getDescuento(),
//                        v.getCantidad(), v.getCantidad()*p.getPrecio());
//            sdp.add(dp);
//        }       
//        return sdp;        
//    }
    public Set<DataCantInd> listarCantInd(){        
        Set<DataCantInd> sdci = new HashSet<>(); 
        
        for(Map.Entry<String, CantInd> entry : individuales.entrySet()) {
            String k = entry.getKey();
            CantInd v = entry.getValue();
            Producto p = v.getPromocion();
            Restaurante r = p.getRestaurante();
            DataProducto dp = new DataProducto(p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getTipo(),
                    r.getNombre(), r.getNickName(), r.getMail(), p.getImagen(), p.getActiva(),  p.getDescuento(),
                    v.getCantidad(), v.getCantidad()*p.getPrecio());
            DataCantInd dci = new DataCantInd(v.getCantidad(), v.getIndividual().getInfo());
            sdci.add(dci);
        }       
        return sdci;        
    }
        @Override
    public DataProducto getInfo(){
        Set<DataPedido> sdp = new HashSet<>();
        return new DataProducto(nombre, descripcion, precio, tipo, this.getRestaurante().getNombre(),
                this.getRestaurante().getNickName(), this.getRestaurante().getMail(), imagen, activa, descuento, 0, 0, this.listarCantInd(), this.listarPedidos());

    }
    
    
    private String guardarImagen(BufferedImage img, String nom, Restaurante r) throws IOException{
        
        String url = "";
        if (img != null){
            String path = escribirImagen(img, r.getNickName(), nom , "promocion");
            url = path + "/" + nom + "_promo.png";
            ImageIO.write(img, "png",new File(url));
        }
        return url;
    }
    
}
    
