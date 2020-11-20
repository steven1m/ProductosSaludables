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
public class Proveedor {
    private int id;
    private String nit;
    private String razonSocial;
    private String direccion;
    private String correo;
    private String telefono;
    private String descripcion;
    private String encargado;

    public Proveedor() {
    }

    public Proveedor(int id, String nit, String razonSocial, String direccion, String correo, String telefono, String descripcion, String encargado) {
        this.id = id;
        this.nit = nit;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.descripcion = descripcion;
        this.encargado = encargado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }
    
    
}
