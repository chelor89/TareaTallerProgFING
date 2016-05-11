package entidades;

public class Usuario {
    
    protected String nickname;
    protected String nombre;
    protected String mail;
    protected String direccion;
    protected String contraseña;
    
    public Usuario(String nick, String nom, String contra, String dir, String mail){
        this.mail = mail;
        this.nombre = nom;
        this.nickname = nick;
        this.direccion = dir;
        this.contraseña = contra;
    }
    
    public String getContrasenia(){
        return this.contraseña;
    }
    
    public String getMail(){
        return mail;
    }
    public String getNombre(){
        return nombre;
    }    
    public String getNickName(){
        return nickname;
    }
    public String getDireccion(){
        return direccion;
    }
    
    
    
    
            
    
}
