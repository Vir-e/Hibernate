package p1;
// Generated 24-nov-2022 23:22:16 by Hibernate Tools 4.3.1



/**
 * Localizaciones generated by hbm2java
 */
public class Localizaciones  implements java.io.Serializable {


     private int idLocalizacion;
     private Libros libros;
     private String nombre;
     private String descripcion;

    public Localizaciones() {
    }

    public Localizaciones(int idLocalizacion, Libros libros, String nombre, String descripcion) {
       this.idLocalizacion = idLocalizacion;
       this.libros = libros;
       this.nombre = nombre;
       this.descripcion = descripcion;
    }
   
    public int getIdLocalizacion() {
        return this.idLocalizacion;
    }
    
    public void setIdLocalizacion(int idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }
    public Libros getLibros() {
        return this.libros;
    }
    
    public void setLibros(Libros libros) {
        this.libros = libros;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }




}

