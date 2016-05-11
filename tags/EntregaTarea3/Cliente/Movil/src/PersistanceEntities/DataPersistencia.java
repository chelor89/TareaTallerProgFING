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
 * @author UT603270
 */
@Entity
public class DataPersistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private int idP;
    private String estado;
    private String nickCliente;
    private String dirCliente;
    private int precioTotal;
    private String fechaPreparacion;
    private String fechaEnviado;
    private String fechRecibido;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public int getIdP() {
        return idP;
    }
    public void setIdP(int idP) {
        this.idP = idP;
    }
    
    public String getEstado() {
        return estado;
    }
    public void setEstado(String e) {
        this.estado = e;
    }
    
    public String getNickCliente() {
        return nickCliente;
    }
    public void setNickCliente(String nc) {
        this.nickCliente = nc;
    }
    
    public String getDirCliente() {
        return dirCliente;
    }
    public void setDirCliente(String dc) {
        this.dirCliente = dc;
    }
    
    public int getPrecioTotal() {
        return precioTotal;
    }
    public void setPrecioTotal(int pt) {
        this.precioTotal = pt;
    }
    
    public String getFechaPreparacion() {
        return fechaPreparacion;
    }
    public void setFechaPreparacion(String fechaPreparacion) {
        this.fechaPreparacion = fechaPreparacion;
    }
    
    public String getFechaEnviado() {
        return fechaEnviado;
    }
    public void setFechaEnviado(String fechaEnviado) {
        this.fechaEnviado = fechaEnviado;
    }
    
    public String getFechaRecibido() {
        return fechRecibido;
    }
    public void setFechaRecibido(String fechRecibido) {
        this.fechRecibido = fechRecibido;
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
        if (!(object instanceof DataPersistencia)) {
            return false;
        }
        DataPersistencia other = (DataPersistencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PersistanceEntities.DataPersistencia[ id=" + id + " ]";
    }
    
}
