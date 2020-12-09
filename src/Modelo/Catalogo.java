
package Modelo;

/**
 *
 * @author Universidad
 */
public class Catalogo {
    
    private int id;
    private String descripcion;
    //private int proveedorId;
    
    public Catalogo(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
        //this.proveedorId = proveedorId;
    }

    public Catalogo() {
        this.id = 0; 
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
