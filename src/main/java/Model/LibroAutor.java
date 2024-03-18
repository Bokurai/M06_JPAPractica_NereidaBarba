package Model;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name="libro_autor")
public class LibroAutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Libro libro;

    public LibroAutor(){}

    public LibroAutor(Autor autor, Libro libro) {
        this.autor = autor;
        this.libro = libro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "LibroAutor{" +
                "autor=" + autor +
                ", libro=" + libro +
                '}';
    }
}
