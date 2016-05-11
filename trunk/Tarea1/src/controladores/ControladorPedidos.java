package controladores;

import dataTypes.DataCliente;
import dataTypes.DataFecha;
import dataTypes.DataPedido;
import dataTypes.DataProducto;
import dataTypes.DataRestaurante;
import dataTypes.Estado;
import entidades.Categoria;
import entidades.Cliente;
import entidades.Individual;
import entidades.Pedido;
import entidades.Producto;
import entidades.Promocion;
import entidades.Restaurante;
import interfaces.IControladorPedidos;
import java.util.Date;
import java.util.Set;
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
           // maxID = 0;
            
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
        // (tipo == 1) = Individuales / (tipo == 2) = Promociones / (tipo == 3) = Todos
        return restauranteActual.listarProductos(tipo);
    }

    @Override
    public Set<DataRestaurante> listarRestaurantesCategoria() {
        return categoriaActual.listarRestaurantes();
    }

    @Override
    public DataPedido seleccionarPedido(int id) {
        ManejadorPedidos mP = ManejadorPedidos.getInstance();
        pedidoActual = mP.obtenerPedido(id);
        return pedidoActual.getInfo();
    }

    @Override
    public void seleccionarCategoria(String cat) {
        ManejadorRestaurantes mr = ManejadorRestaurantes.getInstance();
        categoriaActual = mr.getCategoria(cat);
    }

    @Override
    public void SeleccionarRestaurante(String nombreRes) {
        ManejadorRestaurantes mr = ManejadorRestaurantes.getInstance();
        restauranteActual = mr.getRestaurante(nombreRes);
    }
    
    @Override
    public void SeleccionarProducto (String nombrePro) {
        if(restauranteActual.getIndividuales().containsKey(nombrePro)){
            productoActual = restauranteActual.getIndividuales().get(nombrePro);
        }
        else 
            productoActual = restauranteActual.getPromociones().get(nombrePro);
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
    public void SeleccionarCliente(String nick) {
        ManejadorClientes mc = ManejadorClientes.getInstance();
        clienteActual = mc.getCliente(nick);
    }
    
    @Override
    public void AgregarPedidoACliente() {
        clienteActual.AgregarPedido(pedidoActual);
    }

    
}    