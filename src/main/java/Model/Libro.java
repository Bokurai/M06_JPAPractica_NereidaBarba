package Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Access(AccessType.FIELD)
@Table(name = "libros")
public class Libro implements Serializable {

    @Id
    @Column
    private long id;

    @OneToOne
    @JoinColumn(name = "tema")
    Tema tema;

    @Column(name = "titulo")
    String titulo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "autor")
    public Autor autor;

    @Column(name = "titulo_resumen")
    String titulo_resumen;

    @Column(name = "resumen")
    String resumen;

    public Libro(long id, Tema tema, String titulo, Autor autor, String titulo_resumen, String resumen) {
        super();
        this.id = id;
        this.tema = tema;
        this.titulo = titulo;
        this.autor = autor;
        this.titulo_resumen = titulo_resumen;
        this.resumen = resumen;
    }

    public Libro(){
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
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
                "id=" + id +
                ", tema='" + tema + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", titulo_resumen='" + titulo_resumen + '\'' +
                ", resumen='" + resumen + '\'' +
                '}';
    }
}
