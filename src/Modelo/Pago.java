/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Universidad
 */
public class Pago {
    private int id;
    private float valor;
    private Date fecha;
    private int tipoPagoId;
    private int empleadoId;

    public Pago() {
    }

    public Pago(int id, float valor, Date fecha, int tipoPagoId, int empleadoId) {
        this.id = id;
        this.valor = valor;
        this.fecha = fecha;
        this.tipoPagoId = tipoPagoId;
        this.empleadoId = empleadoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTipoPagoId() {
        return tipoPagoId;
    }

    public void setTipoPagoId(int tipoPagoId) {
        this.tipoPagoId = tipoPagoId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }
    
    
}
