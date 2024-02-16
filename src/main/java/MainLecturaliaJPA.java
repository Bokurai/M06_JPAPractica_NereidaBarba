import Controller.AutorController;
import Controller.LibroController;
import Controller.TemaController;
import View.MenuLecturaliaJPA;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainLecturaliaJPA {

    static SessionFactory sessionFactoryObj;

    private static SessionFactory buildSessionFactory() {
        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure("persistence.xml").build();
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (HibernateException he) {
            System.out.println("Session Factory creation failure");
            throw he;
        }
    }

    public static EntityManagerFactory createEntityManagerFactory() {
        EntityManagerFactory emf;
        try {
            emf = Persistence.createEntityManagerFactory("JPALecturalia");
        } catch (Throwable ex) {
            System.err.println("Failed to create EntityManagerFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return emf;
    }

    public static void main(String[] args) {
       EntityManagerFactory entityManagerFactory = createEntityManagerFactory();
        LibroController libroController = new LibroController(entityManagerFactory);
        TemaController temaController = new TemaController(entityManagerFactory);
        AutorController autorController = new AutorController(entityManagerFactory);
        MenuLecturaliaJPA menu = new MenuLecturaliaJPA();


        int op = menu.menuPrincipal();
        while (op >= 0 && op < 13){
            switch (op) {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    System.out.println("Aquí tiene los temas disponibles:");
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
                    break;

                case 7:
                    break;

                case 8:
                    System.out.println("Aquí tiene los temas disponibles:");
                    break;

                case 9:
                    break;

                case 10:
                    break;

                case 11:
                    break;

                case 12:
                    break;
                default:
                    System.out.println("No es válido");
                    break;
            }
            op = menu.menuPrincipal();
        }
    }
}
