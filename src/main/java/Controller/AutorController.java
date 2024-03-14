package Controller;

import Model.Libro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class AutorController {
    private EntityManagerFactory entityManagerFactory;

    public AutorController(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    public void addStudent(Libro libro) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
        em.close();
    }

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

    public void mostrarLibrosDeUnAutor(Long idAutor) {
        EntityManager em = entityManagerFactory.createEntityManager();
       em.getTransaction().begin();
        List<Libro> libros = em.createNativeQuery("SELECT * FROM libros JOIN libros_autores ON libros.id_libro = libros_autores.id_libro JOIN autores ON libros_autores.id_autor = autores.id_autor WHERE autores.id_autor = :idAutor", Libro.class)
                .setParameter("idAutor", idAutor)
                .getResultList();
        for (Libro libro : libros) {
            System.out.println(libro);
        }
        em.getTransaction().commit();
    }
}
