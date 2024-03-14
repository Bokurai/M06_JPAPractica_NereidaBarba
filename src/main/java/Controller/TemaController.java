package Controller;

import Model.Libro;
import Model.Tema;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class TemaController {
    private EntityManagerFactory entityManagerFactory;

    public TemaController(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void addTema(Tema tema) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(tema);
        em.getTransaction().commit();
        em.close();
    }

    public void listTemas() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Libro> result = em.createQuery("from Student", Libro.class)
                .getResultList();
        System.out.println(result.size());

        for (Libro libro : result) {
            System.out.println(libro.toString());
        }

        System.out.println("STUDENTS");
        em.getTransaction().commit();
        em.close();
    }

    public void updateStudent(Integer libro_id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Libro libro = (Libro) em.find(Libro.class, libro_id);
        em.merge(libro);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteStudent(Integer libro_id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Libro libro = (Libro) em.find(Libro.class, libro_id);
        em.remove(libro);
        em.getTransaction().commit();
        em.close();
    }
}
