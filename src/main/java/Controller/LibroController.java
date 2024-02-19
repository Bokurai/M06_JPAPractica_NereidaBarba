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

    /* Method to CREATE a Student in the database */
    public void addStudent(Libro libro) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to READ all Students */
    public void listStudents() {
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

    /* Method to UPDATE activity for a Student */
    public void updateStudent(Integer studentId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Libro libro = (Libro) em.find(Libro.class, studentId);
        em.merge(libro);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to DELETE a Student from the records */
    public void deleteStudent(Integer studentId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
         Libro libro = (Libro) em.find(Libro.class, studentId);
        em.remove(libro);
        em.getTransaction().commit();
        em.close();
    }
}
