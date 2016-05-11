package controladores;

import dataTypes.DataComentario;
import dataTypes.DataPedido;
import dataTypes.DataProducto;
import dataTypes.DataRestaurante;
import entidades.Categoria;
import entidades.Individual;
import entidades.Pedido;
import entidades.Producto;
import entidades.Promocion;
import entidades.Restaurante;
import interfaces.IControladorRestaurantes;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadores.ManejadorRestaurantes;

public class ControladorRestaurantes implements IControladorRestaurantes {
    
    private Pedido pedidoActual;
    private Categoria categoriaActual;
    private Restaurante restauranteActual;
    private Producto productoActual;
 
    public ControladorRestaurantes(){
        super();
    } 

    @Override
    public Set<DataPedido> listarPedidosRestaurante(String nickResto) {
        try {
            boolean esta;
            seleccionarRestaurante(nickResto);
            Set<DataProducto> productos;
            
            productos = restauranteActual.listarProductos(3);
            
            Set<DataPedido> pedidosResto = new HashSet<>();
            for (DataProducto producto : productos){
                Set<DataPedido> pedidos = producto.getPedidos();
                for (DataPedido pedido : pedidos){
                    esta = false; //cambio
                    for (DataPedido pedido1 : pedidosResto){
                        if (pedido.getId() == pedido1.getId()){
                            esta = true;
                        } 
                    }
                    if (esta == false){
                        pedidosResto.add(pedido);
                    }
                }
            }
            return pedidosResto;
        } catch (IOException ex) {
            Logger.getLogger(ControladorRestaurantes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    @Override
    public void actualizarIndividual(String nombre, String descripcion, BufferedImage imagen, int precio) {
        restauranteActual.getIndividuales().remove(productoActual.getNombre());
        productoActual.setNombre(nombre);
        productoActual.setDescripcion(descripcion);
        productoActual.setImagen(imagen);
        productoActual.setPrecio(precio);
        restauranteActual.getIndividuales().put(nombre, (Individual) productoActual);
    }

    @Override
    public void actualizarPromocion(String nombre, String descripcion, BufferedImage imagen, Boolean activar, int descuento) {
        restauranteActual.getPromociones().remove(productoActual.getNombre());
        productoActual.setNombre(nombre);
        productoActual.setDescripcion(descripcion);
        productoActual.setImagen(imagen);
        productoActual.setDescuento(descuento);
        productoActual.setActiva(activar);
        restauranteActual.getPromociones().put(nombre, (Promocion) productoActual);
    }

    @Override
    public void agregarCategoriasARestaurante() {
        restauranteActual.agregarCategoria(categoriaActual);
        categoriaActual.agregarRestaurante(restauranteActual);
    }

    @Override
    public void agregarIndividualAPromocion(String nombreInd, int cantidad) {
        restauranteActual.agregarIndividualAPromocion(nombreInd,cantidad, (Promocion) productoActual);
        
    }

    @Override
    public boolean altaCategoria(String nombreCat) {
        ManejadorRestaurantes mR = ManejadorRestaurantes.getInstance();
        if (!mR.existeCategoria(nombreCat)) {
          Categoria c = new Categoria(nombreCat);
          mR.agregarCategoria(c);
          return false;
        }
        return true;  
    }

    @Override
    public boolean altaIndividual(String nickRest, String nombre, String descripcion, int precio, BufferedImage imagen) {
        try {
            ManejadorRestaurantes MR = ManejadorRestaurantes.getInstance();
            Restaurante R;
            R = MR.getRestaurante(nickRest);
            if (R.existeProducto(nombre)){
                return true;
            }
            R.crearIndividual(nombre, descripcion, precio, imagen);
            restauranteActual = R;
            return false;
        } catch (IOException ex) {
            Logger.getLogger(ControladorRestaurantes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean altaRestaurante(String nick, String mail, String contra, String nombre, String dir, Set<BufferedImage> imagenes) {
     ManejadorRestaurantes mr = ManejadorRestaurantes.getInstance();
        if(mr.existeRestaurant(nick, mail))
            return true;
        try { 
            restauranteActual = new Restaurante(nick, nombre, contra, dir, mail, imagenes);
        } catch (IOException ex) {
            Logger.getLogger(ControladorRestaurantes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mr.agregarRestaurante(restauranteActual);
        return false;  
    }

    @Override
    public boolean altaPromocion(String nickRest, String nombre, String descripcion, int descuento, int precio, BufferedImage imagen) {
        ManejadorRestaurantes MR = ManejadorRestaurantes.getInstance();
        Restaurante R;
        R = MR.getRestaurante(nickRest);
        if (R.existeProducto(nombre)){
            return true;
        }
        try { 
            R.crearPromocion(nombre, descripcion, descuento, precio, imagen);
        } catch (IOException ex) {
            Logger.getLogger(ControladorRestaurantes.class.getName()).log(Level.SEVERE, null, ex);
        }
        restauranteActual = R;
        return false;
    }

    @Override
    public Set<String> listarCategorias() {
        ManejadorRestaurantes MR = ManejadorRestaurantes.getInstance();
        return (MR.listarCategorias());
    }

    @Override
    public Set<DataProducto> listarProductos() {
       ManejadorRestaurantes MR = ManejadorRestaurantes.getInstance();
       Set<DataRestaurante>  restaurantes = MR.listarRestaurantes();
       Set<DataProducto> productos = new HashSet<>();
       for (DataRestaurante dr : restaurantes){
           Restaurante resto = MR.getRestaurante(dr.getNick());
           try {    
               productos.addAll(resto.listarProductos(3));
           } catch (IOException ex) {
               Logger.getLogger(ControladorRestaurantes.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       return productos;  
    }
    
    @Override
    public Set<DataRestaurante> listarRestaurantes(){
        ManejadorRestaurantes MR = ManejadorRestaurantes.getInstance();
        Collection<Restaurante> aux  = MR.getRestaurantes().values();
        Set<DataRestaurante> restos = new HashSet<>();
        for(Restaurante R:aux){
            try {
                restos.add(R.getInfo());
            } catch (IOException ex) {
                Logger.getLogger(ControladorRestaurantes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return restos;
    }
    
    @Override
    public Set<DataComentario> listarComentarios(){
        Set<DataProducto> prods = listarProductos();
        Set<DataPedido> peds = new HashSet<>();
        for(DataProducto dp : prods){
           peds.addAll(dp.getPedidos());
        }
        Set<DataComentario> coments = new HashSet<>();
        for(DataPedido dp : peds)
            coments.add(dp.getComentario());
        return coments;
    }

    @Override
    public void seleccionarCategoria(String nombreCat) {
        ManejadorRestaurantes MR = ManejadorRestaurantes.getInstance();        
        categoriaActual = MR.getCategoria(nombreCat);
    }

    @Override
    public DataProducto seleccionarProducto(String nombreProd, String nickRest) {
        ManejadorRestaurantes MR = ManejadorRestaurantes.getInstance();
        Restaurante r  = MR.getRestaurante(nickRest);
        Producto p;
        DataProducto DP = null;
        if (r.existeIndividual(nombreProd)){ 
            p = r.getIndividual(nombreProd); 
            DP = new DataProducto(p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getTipo(),
                    r.getNombre(), r.getNickName(), r.getMail(), p.getImagen(), false,  -1, -1, -1);
        }
        else{
            p = r.getPromocion(nombreProd);
            DP = new DataProducto(p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getTipo(),
                    r.getNombre(), r.getNickName(), r.getMail(), p.getImagen(), p.getActiva(),  p.getDescuento(),
                    -1, -1);
        }
        productoActual = p;
        restauranteActual = r;
        return DP;
    }

    @Override
    public boolean seleccionarRestaurante(String key) {
        ManejadorRestaurantes MR = ManejadorRestaurantes.getInstance();    
        
        restauranteActual = MR.getRestaurante(key);
        return restauranteActual != null;
    }

    @Override
    public DataRestaurante verInfoRestauranteM() {      
        try {
            return(restauranteActual.getInfo());
        } catch (IOException ex) {
            Logger.getLogger(ControladorRestaurantes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Set<DataRestaurante> listarRestaurantesCategoria() {
        try {        
            return (categoriaActual.listarRestaurantes());
        } catch (IOException ex) {
            Logger.getLogger(ControladorRestaurantes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean seleccionarProductoDeRestaurante(String nombre) {
        if (restauranteActual.getIndividuales().containsKey(nombre))
            productoActual = restauranteActual.getIndividual(nombre);
        else
            productoActual = restauranteActual.getPromocion(nombre);
        
        return productoActual != null;
    }

    @Override
    public Set<DataPedido> mostrarPedidosQueLoContienen() {
        return productoActual.listarPedidos();
    }

    @Override
    public DataProducto verInfoProducto() {
        return(productoActual.getInfo());
        
    }

    @Override
    public boolean existeRestaurante(String k) {
        ManejadorRestaurantes mr = ManejadorRestaurantes.getInstance();
        return mr.existeRestaurantPorNick(k);
    }

    @Override
    public boolean existeProductoEnRestaurante(String key) {
        return restauranteActual.existeProducto(key);
    }
    
    @Override
    public void addVisitaRestaurante(String restaurante){
        ManejadorRestaurantes mr = ManejadorRestaurantes.getInstance();
        Restaurante res = mr.getRestaurante(restaurante);
        res.addVisita();
    }

    
}
