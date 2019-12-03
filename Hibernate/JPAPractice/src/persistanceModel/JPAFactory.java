package persistanceModel;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAFactory {

    private static final EntityManagerFactory entityManagerFactory;

    private JPAFactory() {}

    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("myDatabaseConfig");
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}