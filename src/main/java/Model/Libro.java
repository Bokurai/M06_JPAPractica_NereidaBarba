package Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Long idLibro;

    @ManyToOne
    @JoinColumn(name = "id_tema")
    private Tema tema;

    @Column(name = "titulo")
    private String titulo;

    @ManyToMany
    @JoinTable(name = "libros_autores",
            joinColumns = @JoinColumn(name = "id_libro"),
            inverseJoinColumns = @JoinColumn(name = "id_autor"))
    private List<Autor> autores;

    @Column(name = "titulo_resumen")
    private String tituloResumen;

    @Column(name = "resumen")
    private String resumen;

    public Libro() {}

    public Libro(Tema tema, String titulo, List<Autor> autores, String tituloResumen, String resumen) {
        this.tema = tema;
        this.titulo = titulo;
        this.autores = autores;
        this.tituloResumen = tituloResumen;
        this.resumen = resumen;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public String getTituloResumen() {
        return tituloResumen;
    }

    public void setTituloResumen(String tituloResumen) {
        this.tituloResumen = tituloResumen;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "idLibro=" + idLibro +
                ", tema=" + tema +
                ", titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", tituloResumen='" + tituloResumen + '\'' +
                ", resumen='" + resumen + '\'' +
                '}';
    }
}

