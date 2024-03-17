package Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Long id_libro;

    @ManyToOne
    @JoinColumn(name = "id_tema")
    private Tema tema;

    @Column(name = "titulo")
    private String titulo;

    @ManyToMany
    @JoinTable(name = "libros_autores",
            joinColumns = @JoinColumn(name = "id_libro"),
            inverseJoinColumns = @JoinColumn(name = "id_autor"))
    private List<Autor> autor;

    @Column(name = "titulo_resumen")
    private String titulo_resumen;

    @Column(name = "resumen")
    private String resumen;

    public Libro() {}

    public Libro(Tema tema, String titulo, List<Autor> autor, String titulo_resumen, String resumen) {
        this.tema = tema;
        this.titulo = titulo;
        this.autor = autor;
        this.titulo_resumen = titulo_resumen;
        this.resumen = resumen;
    }

    public Long getid_libro() {
        return id_libro;
    }

    public void setid_libro(Long id_libro) {
        this.id_libro = id_libro;
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

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        this.autor = autor;
    }

    public String getTitulo_resumen() {
        return titulo_resumen;
    }

    public void setTitulo_resumen(String titulo_resumen) {
        this.titulo_resumen = titulo_resumen;
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
                "idLibro=" + id_libro +
                ", tema=" + tema +
                ", titulo='" + titulo + '\'' +
                ", autores=" + autor +
                ", tituloResumen='" + titulo_resumen + '\'' +
                ", resumen='" + resumen + '\'' +
                '}';
    }
}

