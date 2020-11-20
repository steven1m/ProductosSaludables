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
public class ListaIngredientes {
    private int produccionId;
    private int materiaPrimaId;
    private float cantidad;
    private float costo;

    public ListaIngredientes() {
    }

    public int getProduccionId() {
        return produccionId;
    }

    public void setProduccionId(int produccionId) {
        this.produccionId = produccionId;
    }

    public int getMateriaPrimaId() {
        return materiaPrimaId;
    }

    public void setMateriaPrimaId(int materiaPrimaId) {
        this.materiaPrimaId = materiaPrimaId;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }
    
    
}
