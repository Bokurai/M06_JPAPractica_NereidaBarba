package Controller;

import Model.Libro;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class LibroController {
    private EntityManagerFactory entityManagerFactory;

    public LibroController(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    public void addLibro(Libro libro) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
        em.close();
    }

    public void listLibros() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Libro> result = em.createQuery("from libro", Libro.class)
                .getResultList();
        System.out.println(result.size());

        for (Libro libro : result) {
            System.out.println(libro.toString());
        }

        System.out.println("TODOS LOS LIBROS");
        em.getTransaction().commit();
        em.close();
    }

    public void updateLibro(Integer libro_id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Libro libro = (Libro) em.find(Libro.class, libro_id);
        em.merge(libro);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteLibro(Integer libro_id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
         Libro libro = (Libro) em.find(Libro.class, libro_id);
        em.remove(libro);
        em.getTransaction().commit();
        em.close();
    }
}
