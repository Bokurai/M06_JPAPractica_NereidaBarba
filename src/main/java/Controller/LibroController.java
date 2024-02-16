package Controller;

import javax.persistence.EntityManagerFactory;

public class LibroController {
    private EntityManagerFactory entityManagerFactory;

    public LibroController(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
}
