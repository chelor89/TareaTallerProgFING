package dataTypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataFecha {
    
    private int Anio;
    private int Mes;
    private int Dia;
    private int Hora;
    private int Minuto;
    
    public DataFecha( int anio, int mes, int dia,
            int hora, int minuto){
        
        this.Anio = anio;
        this.Mes = mes;
        this.Dia = dia;
        this.Hora = hora;
        this.Minuto = minuto;
    
    }
    
    public DataFecha(int anio, int mes, int dia){      
        this.Anio = anio;
        this.Mes = mes;
        this.Dia = dia;
        this.Hora = -1;
        this.Minuto = -1;
    }
    
    @Override
    public String toString(){
        if (this.Hora == -1){
            return Dia + "/" + Mes + "/" + Anio;
        }
        
        return Dia + "/" + Mes + "/" + Anio + "-"
                + Hora + ":" + Minuto;
    }
}
