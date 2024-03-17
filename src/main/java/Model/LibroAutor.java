package Model;

import javax.persistence.*;

@Entity
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

}
