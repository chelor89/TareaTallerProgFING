package dataTypes;

public class DataCantInd {
    
    private int cantidad;
    private DataProducto individual;
    
    // dataType para los DataProducto en VerInfoProducto
    public DataCantInd(int cant, DataProducto ind){
        this.cantidad = cant;
        this.individual = ind;
    }
    public DataProducto getIndividual(){
        return individual;
    }
    public int getCantidad(){
        return cantidad;
    }
}
