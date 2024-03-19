package Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tema")
public class Tema {
    @Id
    @Column(name = "id_tema")
    private String idTema;

    @OneToMany(mappedBy = "tema")
    private List<Libro> libros;

    public Tema() {}

    public Tema(String idTema) {
        this.idTema = idTema;
    }

    public String getIdTema() {
        return idTema;
    }

    public void setIdTema(String idTema) {
        this.idTema = idTema;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Tema{" +
                "idTema='" + idTema + '\'' +
                ", libros=" + libros +
                '}';
    }
}