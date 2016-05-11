/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PersistanceEntities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author fabian.rydel
 */
@Entity
public class DataPersProds implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private int idP;
    private String nombre;
    private String tipo;
    private int precioU;
    private int cantidad;
    private int subTotal;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String n){
        this.nombre = n;
    }
    
    public String getTipo(){
        return tipo;
    }
    public void setTipo(String t){
        this.tipo =t;
    }

    public int getPrecioU(){
        return precioU;
    }
    public void setPrecioU(int pu){
        this.precioU = pu;
    }
    
    public int getSubTotal(){
        return subTotal;
    }
    public void setSubTotal(int st){
        this.subTotal = st;
    }
    
    public int getCantidad(){
        return cantidad;
    }
    public void setCantidad(int c){
        this.cantidad = c;
    }
    
    public int getPedido(){
        return idP;
    }
    public void setPedido(int i){
        this.idP = i;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataPersProds)) {
            return false;
        }
        DataPersProds other = (DataPersProds) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PersistanceEntities.DataPersProds[ id=" + id + " ]";
    }
    
}
