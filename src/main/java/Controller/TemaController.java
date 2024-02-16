package Controller;

import javax.persistence.EntityManagerFactory;

public class TemaController {
    private EntityManagerFactory entityManagerFactory;

    public TemaController(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
}
