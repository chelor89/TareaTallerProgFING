/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickorder.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Marcelo
 */
public class Carrito {
    
    private Map<String, ProductoPedido> prods;
    private String restaurante;
    
    public Carrito(){
        this.prods = new HashMap<>();
        this.restaurante = "";
    }
    
    public void setRestaurante(String rest){
        this.restaurante = rest;
    }
    
    public String getRestaurante(){
        return this.restaurante;
    }
    
    public Collection<ProductoPedido> getProductos(){
        return this.prods.values();
    }
    
    public void addProducto(ProductoPedido PP){
        this.prods.put(PP.getNombre(), PP);
    }
    
    public void removeProducto(String nom){
        this.prods.remove(nom);
    }
    
    public boolean containsProducto(String prod){
        return this.prods.containsKey(prod);
    }
    
    public void clear(){
        this.prods.clear();
        this.restaurante = "";
    }
    
    public String toJsonText(){
        Collection<ProductoPedido> prds = this.prods.values();
        String ret = "[";
        for (ProductoPedido pp: prds){
            ret = ret + pp.toJsonText() + ",";
        }
        if (!prds.isEmpty())
            ret = ret.substring(0, ret.length()-1);
        ret = ret + "]";
        return ret;
     }
}
