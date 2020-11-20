
package Modelo;

/**
 *
 * @author Universidad
 */
public class Produccion {
    
    private int id;
    private String nombre;
    private float costo;
    private int productoId;

    public Produccion() {
    }

    public Produccion(int id, String nombre, float costo, int productoId) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
        this.productoId = productoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }
    
    
    
}
