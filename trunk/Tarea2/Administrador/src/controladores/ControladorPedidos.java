package controladores;

import dataTypes.DataCliente;
import dataTypes.DataFecha;
import dataTypes.DataPedido;
import dataTypes.DataProducto;
import dataTypes.DataRestaurante;
import dataTypes.Estado;
import entidades.Categoria;
import entidades.Cliente;
import entidades.Comentario;
import entidades.Individual;
import entidades.Pedido;
import entidades.Producto;
import entidades.Promocion;
import entidades.Restaurante;
import interfaces.IControladorPedidos;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadores.ManejadorClientes;
import manejadores.ManejadorPedidos;
import manejadores.ManejadorRestaurantes;

public class ControladorPedidos implements IControladorPedidos {
    
    private Pedido pedidoActual;
    private Producto productoActual;
    private Set<Producto> productosActules; 
    private Cliente clienteActual;
    private Categoria categoriaActual;
    private Restaurante restauranteActual;
    static int maxID;
    
    public ControladorPedidos(){
            super();
    }

    @Override
    public void altaPedido(DataFecha df) {
        Pedido pedido = new Pedido(maxID +1, clienteActual, df);
        ManejadorPedidos mp = ManejadorPedidos.getInstance();
        mp.agregarPedido(pedido);        
        pedidoActual = pedido;
        maxID++;
    }
    
    @Override
    public void altaPedido() {
        Pedido pedido = new Pedido(maxID +1, clienteActual);
        ManejadorPedidos mp = ManejadorPedidos.getInstance();
        mp.agregarPedido(pedido);        
        pedidoActual = pedido;
        maxID++;
    }

    @Override
    public void agregarProducto(int cant) {
        if(productoActual.getTipo() == Producto.Individual){
            pedidoActual.agregarProducto(cant, (Individual) productoActual);}
        else
            pedidoActual.agregarProducto(cant, (Promocion) productoActual);
    }

    @Override
    public void actualizarEstadoPedido(Estado es) {
        pedidoActual.setEstado(es);
    }

    @Override
    public void cancelarPedidoM() {
        pedidoActual.CancelarPedido();
        ManejadorPedidos mP = ManejadorPedidos.getInstance();
        mP.borrarPedido(pedidoActual);
    }

    @Override
    public Set<DataCliente> listarClientes() {
        ManejadorClientes mc = ManejadorClientes.getInstance();
        return mc.listarClientes();
    }

    @Override
    public Set<String> listarCategorias() {
        ManejadorRestaurantes mr = ManejadorRestaurantes.getInstance();
        return mr.listarCategorias();
    }

    @Override
    public Set<DataPedido> listarPedidos() {
        ManejadorPedidos mP = ManejadorPedidos.getInstance();
        return mP.listarPedidos();
    }

    @Override
    public Set<DataProducto> listarProductosDelRestaurante(int tipo) {
        try {
            return restauranteActual.listarProductos(tipo);
        } catch (IOException ex) {
            Logger.getLogger(ControladorPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Set<DataRestaurante> listarRestaurantesCategoria() {
        try {
            return categoriaActual.listarRestaurantes();
        } catch (IOException ex) {
            Logger.getLogger(ControladorPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public DataPedido seleccionarPedido(int id) {
        ManejadorPedidos mP = ManejadorPedidos.getInstance();
        pedidoActual = mP.obtenerPedido(id);
        return pedidoActual.getInfo();
    }

    @Override
    public boolean seleccionarCategoria(String cat) {
        ManejadorRestaurantes mr = ManejadorRestaurantes.getInstance();
        categoriaActual = mr.getCategoria(cat);
        return categoriaActual != null;
    }

    @Override
    public boolean SeleccionarRestaurante(String nombreRes) {
        ManejadorRestaurantes mr = ManejadorRestaurantes.getInstance();
        restauranteActual = mr.getRestaurante(nombreRes);
        return restauranteActual != null;
    }
    
    @Override
    public boolean SeleccionarProducto (String nombrePro) {
        if(restauranteActual.getIndividuales().containsKey(nombrePro)){
            productoActual = restauranteActual.getIndividuales().get(nombrePro);
        }
        else 
            productoActual = restauranteActual.getPromociones().get(nombrePro);
        
        return productoActual != null;
    }

    @Override
    public DataPedido verInfoPedido() {
        return(pedidoActual.getInfo());
    }
    
    @Override
    public DataPedido verInfoPedidoGenerado() {
        return(pedidoActual.getInfoPedidoGenerado());
    }

    @Override
    public boolean SeleccionarCliente(String nick) {
        ManejadorClientes mc = ManejadorClientes.getInstance();
        clienteActual = mc.getCliente(nick);
        return clienteActual!=null;
    }
    
    @Override
    public void AgregarPedidoACliente() {
        clienteActual.AgregarPedido(pedidoActual);
    }

    @Override
    public void AgregarComentarioAPedido(String contenido, int puntaje){
        pedidoActual.getRestaurante().setPuntaje(puntaje);
        pedidoActual.setComentario(new Comentario(contenido, puntaje));
        pedidoActual.getComentario().setPedido(pedidoActual);
    }
    
    @Override
    public void AgregarComentarioAPedido(String contenido, int puntaje, DataFecha fecha){
        pedidoActual.getRestaurante().setPuntaje(puntaje);
        pedidoActual.setComentario(new Comentario(contenido, puntaje, fecha));
        pedidoActual.getComentario().setPedido(pedidoActual);
    }
    
}    