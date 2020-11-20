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
public class Factura {
    private int id;
    private String clienteId;
    private int empleadoId;
    private int padoId;
    private int tipoVentaId;
    private Date fecha;

    public Factura() {
    }

    public Factura(int id, String clienteId, int empleadoId, int padoId, int tipoVentaId, Date fecha) {
        this.id = id;
        this.clienteId = clienteId;
        this.empleadoId = empleadoId;
        this.padoId = padoId;
        this.tipoVentaId = tipoVentaId;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public int getPadoId() {
        return padoId;
    }

    public void setPadoId(int padoId) {
        this.padoId = padoId;
    }

    public int getTipoVentaId() {
        return tipoVentaId;
    }

    public void setTipoVentaId(int tipoVentaId) {
        this.tipoVentaId = tipoVentaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
