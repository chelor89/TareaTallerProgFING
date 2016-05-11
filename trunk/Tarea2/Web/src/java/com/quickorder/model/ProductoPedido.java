/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickorder.model;

import dataTypes.DataProducto;

/**
 *
 * @author Marcelo
 */
public class ProductoPedido {
    
     private String nombre;
     private int precio;
     private int cantidad;
     
     public ProductoPedido(DataProducto dp, int cant){
         this.nombre = dp.getNombre();
         this.precio = dp.getPrecioUnitario();
         this.cantidad = cant;
     }
     
     public void setCantidad(int cant){
         this.cantidad = cant;
     }
     
     public String getNombre(){
        return this.nombre;
     }
     
     public int getCantidad(){
         return this.cantidad;
     }
     
     public String toJsonText(){
         return "{ \"nombre\":\"" + this.nombre + "\" , \"precio\":\"" + this.precio + "\" , \"cantidad\":\"" + this.cantidad + "\" }";
     }
     
}
