package Controller;

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
        List<Tema> result = em.createQuery("from temas", Tema.class)
                .getResultList();
        System.out.println(result.size());

        for (Tema tema : result) {
            System.out.println(tema.toString());
        }

        System.out.println("TODOS LOS TEMAS");
        em.getTransaction().commit();
        em.close();
    }

    public void updateTema(String tema_id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Tema tema = (Tema) em.find(Tema.class, tema_id);
        em.merge(tema);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteStudent(String tema_id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Tema tema = (Tema) em.find(Tema.class, tema_id);
        em.remove(tema);
        em.getTransaction().commit();
        em.close();
    }
}
