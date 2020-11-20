
package Modelo;

/**
 *
 * @author Universidad
 */
public class DetalleCatalogo {
    
    private int id;
    private int catalogoId;
    private String nombre;
    private String descripcion;
    private float precio;
    private int materiaPrimaId;

    public DetalleCatalogo() {
    }

    public DetalleCatalogo(int id, int catalogoId, String nombre, String descripcion, float precio, int materiaPrimaId) {
        this.id = id;
        this.catalogoId = catalogoId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.materiaPrimaId = materiaPrimaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCatalogoId() {
        return catalogoId;
    }

    public void setCatalogoId(int catalogoId) {
        this.catalogoId = catalogoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getMateriaPrimaId() {
        return materiaPrimaId;
    }

    public void setMateriaPrimaId(int materiaPrimaId) {
        this.materiaPrimaId = materiaPrimaId;
    }
    
    
}
