package entidades;

public class CantInd {
    
    private int cantidad;
    private Promocion promocion;
    private Individual individual;
    
    public CantInd(int cant, Individual indi, Promocion promo){
        this.cantidad = cant;
        this.promocion = promo;
        this.individual = indi;
    }
    
    public int getCantidad(){
        return cantidad;
    }
    
    public Promocion getPromocion(){
        return promocion;
    }
    
    public Individual getIndividual(){
        return individual;
    }

}