package entidades;

import dataTypes.DataCliente;
import dataTypes.DataFecha;
import dataTypes.DataPedido;
import static entidades.ImagenesServidor.escribirImagen;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Cliente extends Usuario{
 
    private String apellido;
    private String imagen;
    private DataFecha fechaNacimiento;
    private Map<Integer, Pedido> pedidos; 
    

    
    // constructor con imagen
    public Cliente(String nick, String nom, String contra, String dir, String mail,
            String apellido, DataFecha fN, BufferedImage img) throws IOException{
        super(nick, nom, contra, dir, mail);
        
        this.apellido = apellido;
        this.fechaNacimiento = fN;
        
        if (img != null){
            String path = escribirImagen(img, nick, "", "cliente");
            this.imagen = path + "/" + nick + "_perfil.png";
        }else this.imagen = "";
        
        pedidos = new HashMap<>();
    }
    
    public DataFecha getFechaNacimiento(){
        return fechaNacimiento;
    }
    
    public String getApellido(){
        return apellido;
    }
    
    public String getImagen(){
        return imagen;
    }
    
    public Map<Integer, Pedido> getPedidos(){
        return pedidos;
    }
    
    public Pedido getPedido(int key){
        return pedidos.get(key);
    }
            
    
    // obtener dataTypes de pedidos   
    public Set<DataPedido> listarPedidos(){   
        Set<DataPedido> sdp = new HashSet<>(); 
        
        for(Entry<Integer, Pedido> entry : pedidos.entrySet()) {
           
            Pedido v = entry.getValue();
            
                    
            DataPedido dp =  (v.getInfo());  
            sdp.add(dp);
        }       
        return sdp;        
      
    }            
    public DataCliente getInfo() throws IOException{
        Set<DataPedido> sdp = new HashSet<>();
         for(Entry<Integer, Pedido> entry : pedidos.entrySet()) {
         
            Pedido v = entry.getValue();          
                      
            DataPedido dp = v.getInfo();
            sdp.add(dp);
         }   
        
        return(new DataCliente(nickname, mail, contraseña, nombre, direccion, apellido, fechaNacimiento, imagen, sdp));
    }
    
    public void AgregarPedido(Pedido p){
        pedidos.put(p.getId(), p);
    }
}
