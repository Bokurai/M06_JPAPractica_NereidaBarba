package Controller;

import Model.Autor;
import Model.Libro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class AutorController {
    private EntityManagerFactory entityManagerFactory;

    public AutorController(){
    }
    public AutorController(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    public void addAutor(Autor autor) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(autor);
        em.getTransaction().commit();
        em.close();
    }

    public void listAutors() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Autor> result = em.createQuery("from autor", Autor.class)
                .getResultList();
        System.out.println(result.size());

        for (Autor autor: result) {
            System.out.println(autor.toString());
        }

        System.out.println("TODOS LOS AUTORES");
        em.getTransaction().commit();
        em.close();
    }

    public void updateAutor(Integer libro_id) {
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

    public void listLibrosFromAutor(Long idAutor) {
        EntityManager em = entityManagerFactory.createEntityManager();
       em.getTransaction().begin();
        List<Libro> libros = em.createQuery("SELECT * FROM libros JOIN libros_autores ON libros.id_libro = libros_autores.id_libro JOIN autores ON libros_autores.id_autor = autores.id_autor WHERE autores.id_autor = :idAutor", Libro.class)
                .setParameter("id_autor", idAutor)
                .getResultList();
        for (Libro libro : libros) {
            System.out.println(libro);
        }
        em.getTransaction().commit();
    }
}
