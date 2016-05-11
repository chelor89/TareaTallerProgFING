package entidades;

import dataTypes.DataPedido;
import dataTypes.DataProducto;
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

public class Individual extends Producto {
    
    private Map<String,CantInd> promociones;
   
    
    public Individual(String nom, String descripcion, int p, Restaurante r, BufferedImage img) throws IOException{
        super(nom, descripcion, r);
        
        
        String url = guardarImagen(img,nom,restaurante);
        this.imagen = url;
        this.precio = p;
        this.tipo = Individual;
        this.promociones = new HashMap<>(); 
    }
    
    public void agregarPromoAInd(CantInd CI){
        promociones.put(CI.getPromocion().getNombre(), CI);
    }
    
    @Override
    public boolean getActiva(){
        return false;
    }
    
    @Override
    public int getDescuento(){
        return 0;
    }
 // (String nom, String desc, int precio, String imagen, Set<DataPedido> pedidos)
    @Override
    public DataProducto getInfo(){
        Set<DataPedido> sdp = new HashSet<>();
        for(Map.Entry<Integer, PedProd> entry : this.getPedidos().entrySet()) {            
            int k = entry.getKey();
            PedProd v = entry.getValue();
            
            sdp.add(v.getPedido().getInfo());
        }
        return new DataProducto(nombre, descripcion, precio, imagen, sdp);
    }
    
//    @Override
//    public void modificarProducto(String nombre, String descripcion, String imagen, int precio, boolean activa, int descuento){
//        this.precio = precio;
//    }
    
    @Override
    public void setImagen(BufferedImage im){
        try {
            this.imagen = guardarImagen(im,this.getNombre(),this.getRestaurante());
        } catch (IOException ex) {
            Logger.getLogger(Individual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void setActiva(boolean v) {
   }

    @Override
    public void setDescuento(int desc) {
   }

    
    private String guardarImagen(BufferedImage img, String nom, Restaurante r) throws IOException{
        String url = "";
        if (img != null){
            /* GUARDA LA IMAGEN */
                String workingDir;
                String uploadFilePath;

                String OS =System.getProperty("os.name").toLowerCase();
                if (OS.contains("win")){
                    workingDir = System.getenv("APPDATA");
                }else{
                    workingDir = System.getProperty("user.home");
                }
                
                uploadFilePath = workingDir + "/QuickOrder/Restaurantes/" + r.getNickName();
                /*Crea la ruta si no existe*/
                File fileSaveDir = new File(uploadFilePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }
                /*******************/
            ImageIO.write(img, "png",new File(uploadFilePath + "/" + nom + "_indi.png"));
            url = uploadFilePath + "/" + nom + "_indi.png";
            /**********************************/
        }
        return url;
    }
    
}
