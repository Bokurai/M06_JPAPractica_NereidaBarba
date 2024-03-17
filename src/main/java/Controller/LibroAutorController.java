package Controller;

import Model.Autor;
import Model.Libro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class LibroAutorController {
    private EntityManagerFactory entityManagerFactory;

    public LibroAutorController(){}

    public LibroAutorController(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Libro> obtenerLibrosDeAutor(Long idAutor) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT la.libro FROM LibroAutor la WHERE la.autor.idAutor = :idAutor");
        query.setParameter("idAutor", idAutor);
        List<Libro> libros = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return libros;
    }

    public List<Autor> obtenerAutoresDeLibro(Long idLibro) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT la.autor FROM LibroAutor la WHERE la.libro.idLibro = :idLibro");
        query.setParameter("idLibro", idLibro);
        List<Autor> autores = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return autores;
    }
}
