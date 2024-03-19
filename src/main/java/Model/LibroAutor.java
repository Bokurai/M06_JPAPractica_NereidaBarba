package Model;

import javax.persistence.*;

@Entity
@Table(name="libro_autor")
public class LibroAutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
