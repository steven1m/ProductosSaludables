/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Universidad
 */
public class TipoVenta {
    private int id;
    private String descripcion;

    public TipoVenta() {
    }

    public TipoVenta(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
