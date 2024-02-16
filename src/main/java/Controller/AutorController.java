package Controller;

import javax.persistence.EntityManagerFactory;

public class AutorController {
    private EntityManagerFactory entityManagerFactory;

    public AutorController(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

}
