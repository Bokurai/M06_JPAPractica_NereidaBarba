package Controller;

import Model.Autor;
import Model.Libro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Clase Controller para Autor
 */
public class AutorController {
    private EntityManagerFactory entityManagerFactory;

    /**
     * Constructor vacío para Hibernate
     */
    public AutorController(){
    }

    /**
     *
     * Constructor con EntityManager
     * @param entityManagerFactory
     */
    public AutorController(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Añadir autor
     * @param autor
     */
    public void addAutor(Autor autor) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(autor);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Listar autores
     */
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

    /**
     * Actualizar datos de autor
     * @param autor_id
     */
    public void updateAutor(Integer autor_id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Autor autor = (Autor) em.find(Autor.class, autor_id);
        em.merge(autor);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Eliminar autor
     * @param autor_id
     */
    public void deleteAutor(Integer autor_id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Autor autor = (Autor) em.find(Autor.class, autor_id);
        em.remove(autor);
        em.getTransaction().commit();
        em.close();
    }

}
