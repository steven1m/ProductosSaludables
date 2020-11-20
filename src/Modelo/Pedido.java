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
public class Pedido {
    private int id;
    private Date fechaPedido;
    private Date fechaDespacho;
    private int empleadoId;
    private String clienteId;
    private int estadoPedidoId;
    private int facturaId;

    public Pedido() {
    }

    public Pedido(int id, Date fechaPedido, Date fechaDespacho, int empleadoId, String clienteId, int estadoPedidoId, int facturaId) {
        this.id = id;
        this.fechaPedido = fechaPedido;
        this.fechaDespacho = fechaDespacho;
        this.empleadoId = empleadoId;
        this.clienteId = clienteId;
        this.estadoPedidoId = estadoPedidoId;
        this.facturaId = facturaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Date getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(Date fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public int getEstadoPedidoId() {
        return estadoPedidoId;
    }

    public void setEstadoPedidoId(int estadoPedidoId) {
        this.estadoPedidoId = estadoPedidoId;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }
    
    
}
