package Publicar;

import Fabrica.Fabrica;
import dataTypes.DataProducto;
import dataTypes.DataRestaurante;
import entidades.ImagenesServidor;
import interfaces.IControladorRestaurantes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

/**
 *
 * @author Fede
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class PublicadorBusqueda {

    public static class categorias {

        public String[] categorias;
        public int[] cantRestaurantes;
        
        categorias(){}
        categorias(String[] cats, int[] cant) {
            categorias = cats;
            cantRestaurantes = cant;
        }
    }
    private Endpoint endpoint = null;
    Fabrica F = Fabrica.getInstance();
    private IControladorRestaurantes cr = F.getIControladorRestaurantes();

    public PublicadorBusqueda() {
    }

    @WebMethod(exclude = true)
    public void publicar() {
          
            endpoint = Endpoint.publish("http://" + ImagenesServidor.urlWS + "/publicadorBusqueda", this);

    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public DataRestaurante[] buscar(String busqueda) {
     //   Restos r = new Restos();
        DataRestaurante[] r;
        if (busqueda.equals("")) {
            r = cr.listarRestaurantes().toArray(new DataRestaurante[cr.listarRestaurantes().size()]);
           // r.rests.addAll(cr.listarRestaurantes());
        } else {
            Map<String, DataRestaurante> mapRestos = new HashMap<>();
            for (DataRestaurante dr : cr.listarRestaurantes()) {
                if (dr.getNombre().toLowerCase().contains(busqueda.toLowerCase())) {
                    if (!mapRestos.containsKey(dr.getNick())) {
                        mapRestos.put(dr.getNick(), dr);
                    }
                }
            }
            for (DataProducto dp : cr.listarProductos()) {
                if (dp.getNombre().toLowerCase().contains(busqueda.toLowerCase())) {
                    cr.seleccionarRestaurante(dp.getNickRes());
                    if (!mapRestos.containsKey(dp.getNickRes())) {
                        mapRestos.put(dp.getNickRes(), cr.verInfoRestauranteM());
                    }
                }
            }
           r =  mapRestos.values().toArray(new DataRestaurante[mapRestos.size()]);
           // r.rests.addAll(mapRestos.values());
        }
        return r;
    }

    @WebMethod
    public categorias getCategorias(boolean ordenadas) {
        categorias c;

        int cantCategorias = cr.listarCategorias().size();
        String[] cats;
        int[] cant;

        if (ordenadas) {
            c = ordenarCategorias(cr);            
        } else {
            cant = new int[cantCategorias];
            cats = cr.listarCategorias().toArray(new String[cantCategorias]);
            for (int i = 0; i < cantCategorias; i++) {
                cr.seleccionarCategoria(cats[i]);
                cant[i] = cr.listarRestaurantesCategoria().size();                
            }
            c = new categorias(cats, cant);
        }
        return c;
    }

    @WebMethod
    public DataRestaurante[] listarPorCategoria(String cat) {
        cr.seleccionarCategoria(cat);
//        Restos r = new Restos();
//        r.rests.addAll(cr.listarRestaurantesCategoria());
        
        return cr.listarRestaurantesCategoria().toArray(new DataRestaurante[cr.listarRestaurantesCategoria().size()]);
    }

    @WebMethod(exclude = true)
    public categorias ordenarCategorias(IControladorRestaurantes cr) {

        int max = 0;
        String maxcat = "";

        Set<String> aux = new HashSet<>();
        aux.addAll(cr.listarCategorias());
        String[] resCats = new String[aux.size()];
        int[] cantRests = new int[aux.size()];
        int i = 0;
        while (!aux.isEmpty()) {
            for (String cat : aux) {
                cr.seleccionarCategoria(cat);
                if (cr.listarRestaurantesCategoria().size() >= max) {
                    maxcat = cat;
                    max = cr.listarRestaurantesCategoria().size();
                }
            }
            aux.remove(maxcat);
            resCats[i] = (maxcat);
            cantRests[i] = max;
            max = 0;
            i++;
        }
        return (new categorias(resCats, cantRests));
    }
}
