
package Modelo;

/**
 *
 * @author Universidad
 */
public class Catalogo {
    
    private int id;
    private String descripcion;
    private int proveedorId;
    
    public Catalogo(int id, String descripcion, int proveedorId) {
        this.id = id;
        this.descripcion = descripcion;
        this.proveedorId = proveedorId;
    }

    public Catalogo() {
        this.id = 0;
        this.descripcion = "";
        this.proveedorId = 0;  
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

    public int getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;
    }
    
        
}
