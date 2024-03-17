package Controller;

import Model.Autor;
import Model.Libro;
import Model.LibroAutor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LibroAutorController {
    private EntityManagerFactory entityManagerFactory;

    public LibroAutorController() {
    }

    public LibroAutorController(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Libro> librosPorAutor(Long id_autor) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Libro> libros = new ArrayList<>();
        try {
            em.getTransaction().begin();
            Autor autor = em.find(Autor.class, id_autor);
            if (autor != null) {
                libros = autor.getLibros();
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return libros;
    }

    public List<Autor> autoresPorLibro(Long id_libro) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Autor> autores = new ArrayList<>();
        try {
            em.getTransaction().begin();
            Libro libro = em.find(Libro.class, id_libro);
            if (libro != null) {
                autores = libro.getAutor();
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return autores;
    }


    public void listTablaLibroAutor() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<LibroAutor> result = em.createQuery("from libro_autor", LibroAutor.class)
                .getResultList();
        for (LibroAutor libroAutor : result) {
            System.out.println(libroAutor.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

}
