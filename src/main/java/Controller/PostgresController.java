package Controller;

import javax.persistence.EntityManagerFactory;

public class PostgresController {
    private EntityManagerFactory entityManagerFactory;

    public PostgresController(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
}
