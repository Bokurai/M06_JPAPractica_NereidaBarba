import Controller.*;
import Model.Autor;
import Model.Libro;
import Model.Tema;
import View.MenuLecturaliaJPA;
import com.opencsv.exceptions.CsvValidationException;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public static void main(String[] args) throws CsvValidationException, IOException {
        EntityManagerFactory entityManagerFactory = createEntityManagerFactory();
        LibroController libroController = new LibroController(entityManagerFactory);
        TemaController temaController = new TemaController(entityManagerFactory);
        AutorController autorController = new AutorController(entityManagerFactory);
        LibroAutorController libroAutorController = new LibroAutorController(entityManagerFactory);
        PostgresController postgresController = new PostgresController(entityManagerFactory);
        MenuLecturaliaJPA menu = new MenuLecturaliaJPA();

        Scanner scanner = new Scanner(System.in);

        int op = menu.menuPrincipal();
        while (op >= 0 && op < 19){
            switch (op) {
                case 1:
                    libroController.listLibros();
                    break;

                case 2:
                    temaController.listTemas();
                    break;

                case 3:
                    autorController.listAutors();
                    break;

                case 4:
                    libroAutorController.listTablaLibroAutor();
                    break;

                case 5:
                    int libroporautorid = scanner.nextInt();
                    libroAutorController.librosPorAutor(libroporautorid);
                    break;

                case 6:
                    int autorporlibrois = scanner.nextInt();
                    libroAutorController.autoresPorLibro(autorporlibrois);
                    break;

                case 7:
                    System.out.println("Ingrese el ID del tema:");
                    String idTema = scanner.nextLine();
                    System.out.println("Ingrese el título del libro:");
                    String titulo = scanner.nextLine();
                    System.out.println("Ingrese el título del resumen:");
                    String tituloResumen = scanner.nextLine();
                    System.out.println("Ingrese el resumen:");
                    String resumen = scanner.nextLine();

                    Tema tema = new Tema();
                    tema.setIdTema(idTema);
                    List<Autor> autores = new ArrayList<>();
                    Libro libro = new Libro(tema, titulo, autores, tituloResumen, resumen);

                    libroController.addLibro(libro);
                    break;

                case 8:
                    System.out.println("Ingrese el nombre del autor:");
                    String nombreAutor = scanner.nextLine();
                    Autor autor = new Autor(nombreAutor);
                    autorController.addAutor(autor);
                    break;

                case 9:
                    System.out.println("Ingrese el nombre del tema");
                    String nombre_tema = scanner.nextLine();
                    Tema temanuevo = new Tema(nombre_tema);
                    temaController.addTema(temanuevo);
                    break;

                case 10:
                    int libroactu_id = scanner.nextInt();
                    libroController.updateLibro(libroactu_id);
                    break;

                case 11:
                    int autoractu_id = scanner.nextInt();
                    autorController.updateAutor(autoractu_id);
                    break;

                case 12:
                    String temaactu_id = scanner.nextLine();
                    temaController.updateTema(temaactu_id);
                    break;

                case 13:
                    System.out.println("Aquí todos los libros con el titulo y la id para localizar:");
                    libroController.listLibros();
                    System.out.println("Introduzca la id del libro:");
                    int libro_id = scanner.nextInt();
                    libroController.deleteLibro(libro_id);
                    break;


                case 14:

                    System.out.println("Aquí todos los autores con su id y nombre para localizar");
                    autorController.listAutors();
                    System.out.println("Introduzca la id del autor:");
                    int autor_id = scanner.nextInt();
                    autorController.deleteAutor(autor_id);
                    break;


                case 15:
                    System.out.println("Aquí todos los temas para localizar el que desee eliminar");
                    temaController.listTemas();
                    System.out.println("Introduzca el nombre del tema");
                    String tema_id = scanner.nextLine();
                    temaController.deleteTema(tema_id);
                    break;


                case 16:
                    postgresController.poblarTablaAutores();
                    postgresController.poblarTablaTemas();
                    postgresController.poblarTablaLibros();
                    postgresController.poblarLibroAutor();
                    break;

                case 17:
                    postgresController.borrarTablaLibroAutor();
                    postgresController.borrarTablaLibro();
                    postgresController.borrarTablaAutor();
                    postgresController.borrarTablaTema();
                    break;

                case 18:
                    postgresController.crearTablaAutor();
                    postgresController.crearTablaLibro();
                    postgresController.crearTablaTema();
                    postgresController.crearTablaLibroAutor();
                    break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }
            op = menu.menuPrincipal();
        }

    }
}
