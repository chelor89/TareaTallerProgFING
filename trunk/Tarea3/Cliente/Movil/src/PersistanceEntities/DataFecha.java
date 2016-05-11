package PersistanceEntities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
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
    
    @Override
    public String toString(){
        if (this.Hora == -1){
            return Dia + "/" + Mes + "/" + Año;
        }
        
        return Dia + "/" + Mes + "/" + Año + "-"
                + Hora + ":" + Minuto;
    }
}
