package Model;

import javax.persistence.*;
import java.util.List;


/**
 * Clase modelo de autor
 */
@Entity
@Table(name = "autor")
public class Autor {

    /** El identificador único del autor. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor")
    private int id_autor;

    /** El nombre del autor. */
    @Column(name = "nombre")
    private String nombre;

    /** La lista de libros escritos por este autor. */
    @ManyToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Libro> libros;

    /** Constructor por defecto. */
    public Autor() {}


    /**
     * Constructor que toma el nombre del autor.
     *
     * @param nombre El nombre del autor.
     */
    public Autor(String nombre) {
        this.nombre = nombre;
    }


    /**
     * Obtiene el identificador único del autor.
     *
     * @return El identificador único del autor.
     */
    public int getIdAutor() {
        return id_autor;
    }


    /**
     * Establece el identificador único del autor.
     *
     * @param id_autor El identificador único del autor.
     */
    public void setIdAutor(int id_autor) {
        this.id_autor = id_autor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }


}