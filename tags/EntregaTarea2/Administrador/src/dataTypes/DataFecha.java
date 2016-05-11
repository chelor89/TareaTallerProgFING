package dataTypes;

public class DataFecha {
    
    private int Año;
    private int Mes;
    private int Dia;
    private int Hora;
    private int Minuto;
    
    public DataFecha( int año, int mes, int dia,
            int hora, int minuto){
        
        this.Año = año;
        this.Mes = mes;
        this.Dia = dia;
        this.Hora = hora;
        this.Minuto = minuto;
    
    }
    
    public DataFecha(int año, int mes, int dia){      
        this.Año = año;
        this.Mes = mes;
        this.Dia = dia;
        this.Hora = -1;
        this.Minuto = -1;
    }
    
 /*   public DataFecha(int hora, int minuto){        
        this.Hora = hora;
        this.Minuto = minuto;   
    }*/   
    
    @Override
    public String toString(){
        if (this.Hora == -1){
            return Dia + "/" + Mes + "/" + Año;
        }
        
        return Dia + "/" + Mes + "/" + Año + "-"
                + Hora + ":" + Minuto;
    }
    /*
    public int getAño(){
        return Año;
    }
    public int getMes(){
        return Mes;
    }
    public int getDia(){
        return Dia;
    }
    public int getHora(){
        return Hora;
    }
    public int getMinuto(){
        return Minuto;
    }*/
}
