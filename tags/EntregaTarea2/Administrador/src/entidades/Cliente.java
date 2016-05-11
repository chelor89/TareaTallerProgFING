package entidades;

import dataTypes.DataCliente;
import dataTypes.DataFecha;
import dataTypes.DataPedido;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

public class Cliente extends Usuario{
 
    private String apellido;
    private String imagen;
    private DataFecha fechaNacimiento;
    private String contrasenia;
    private Map<Integer, Pedido> pedidos; 
    

    
    // constructor con imagen
    public Cliente(String nick, String nom, String dir, String mail,
            String apellido, DataFecha fN, String contra,BufferedImage img) throws IOException{
        super(nick, nom, dir, mail);
        
        this.apellido = apellido;
        this.fechaNacimiento = fN;
        
        if (img != null){
            /* GUARDA LA IMAGEN */
            String OS =System.getProperty("os.name").toLowerCase();
            String workingDir;
            String uploadFilePath;
            if (OS.contains("win")){
                workingDir = System.getenv("APPDATA");
            }
            else{
                workingDir = System.getProperty("user.home");
            }
            
                uploadFilePath = workingDir + "/QuickOrder/Clientes";
                /*Crea la ruta si no existe*/
                File fileSaveDir = new File(uploadFilePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }
                /*******************/
            ImageIO.write(img, "png",new File(uploadFilePath + "/" + nick + "_perfil.png"));
            this.imagen = uploadFilePath+ "/" + nick + "_perfil.png";
            /**********************************/
        }else this.imagen = "";
        
        this.contrasenia = contra;
        
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
    
    public String getContrasenia(){
        return this.contrasenia;
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
        
        return(new DataCliente(nickname, mail, nombre, direccion, apellido, fechaNacimiento, imagen, sdp, contrasenia));
    }
    
    public void AgregarPedido(Pedido p){
        pedidos.put(p.getId(), p);
    }
}
