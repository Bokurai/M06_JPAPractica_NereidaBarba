package Controller;

import Model.Autor;
import Model.Libro;
import Model.Tema;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PostgresController {

    private EntityManagerFactory entityManagerFactory;

    public PostgresController() {
    }

    public PostgresController(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void crearTablaAutor() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("CREATE TABLE IF NOT EXISTS autor ("
                + "id_autor SERIAL PRIMARY KEY, "
                + "nombre VARCHAR(255), "
                + "apellidos VARCHAR(255)"
                + ")").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void crearTablaLibro() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("CREATE TABLE IF NOT EXISTS libro ("
                + "id_libro SERIAL PRIMARY KEY, "
                + "id_tema VARCHAR(255), "
                + "titulo VARCHAR(255), "
                + "titulo_resumen VARCHAR(255), "
                + "resumen TEXT, "
                + "FOREIGN KEY (id_tema) REFERENCES tema(id_tema)"
                + ")").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void crearTablaTema() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("CREATE TABLE IF NOT EXISTS tema ("
                + "id_tema VARCHAR(255) PRIMARY KEY"
                + ")").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void crearTablaLibroAutor() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("CREATE TABLE IF NOT EXISTS libro_autor ("
                + "id_libro INT, "
                + "id_autor INT, "
                + "PRIMARY KEY (id_libro, id_autor), "
                + "FOREIGN KEY (id_libro) REFERENCES libro(id_libro), "
                + "FOREIGN KEY (id_autor) REFERENCES autor(id_autor)"
                + ")").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }


    //Métodos para poblar las tablas

    public void poblarTablaLibros() throws IOException, CsvValidationException {
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();

            CSVReader csvReader = new CSVReader(new FileReader("resources/temas_y_libros.csv"));
            String[] nextLine;

            while ((nextLine = csvReader.readNext()) != null) {
                String idTema = nextLine[0];
                String titulo = nextLine[1];
                String[] autores = nextLine[2].split(",");
                String tituloResumen = nextLine[3];
                String resumen = nextLine[4];

                Tema tema = em.find(Tema.class, idTema);
                if (tema == null) {
                    tema = new Tema(idTema);
                    em.persist(tema);
                }

                List<Autor> autorList = new ArrayList<>();
                for (String nombreAutor : autores) {
                    Autor autor = em.createQuery("SELECT a FROM Autor a WHERE a.nombre = :nombre", Autor.class)
                            .setParameter("nombre", nombreAutor.trim())
                            .getSingleResult();
                    autorList.add(autor);
                }

                Libro libro = new Libro(tema, titulo, autorList, tituloResumen, resumen);
                em.persist(libro);
                tema.getLibros().add(libro);
            }

            csvReader.close();
            em.getTransaction().commit();
            System.out.println("Se ha poblado la tabla libros con los datos correspondientes.");
        } catch (Exception e) {
            if (em.getTransaction() != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }




    public void poblarTablaAutores() throws IOException, CsvValidationException {
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();

            CSVReader csvReader = new CSVReader(new FileReader("resources/autores.csv"));
            String[] nextLine;

            while ((nextLine = csvReader.readNext()) != null) {
                String nombre = nextLine[0];

                Autor autor = new Autor();
                autor.setNombre(nombre);
                em.persist(autor);
            }

            csvReader.close();
            em.getTransaction().commit();
            System.out.println("Tabla autor poblada.");
        } catch (Exception e) {
            if (em.getTransaction() != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void poblarTablaTemas() throws IOException, CsvValidationException {
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();

            CSVReader csvReader = new CSVReader(new FileReader("resources/temas_y_libros.csv"));
            String[] nextLine;

            while ((nextLine = csvReader.readNext()) != null) {
                String tema_id = nextLine[0];

                Tema tema = new Tema();
                tema.setIdTema(tema_id);
                em.persist(tema);
            }
            csvReader.close();
            em.getTransaction().commit();
            System.out.println("Tabla autor creada");
        } catch (Exception e) {
            if (em.getTransaction() != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void poblarLibroAutor() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        try {
            List<Libro> libros = em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();

            for (Libro libro : libros) {
                List<Autor> autores = libro.getAutor();

                for (Autor autor : autores) {
                    em.createNativeQuery("INSERT INTO libro_autor (id_libro, id_autor) VALUES (:idLibro, :idAutor)")
                            .setParameter("idLibro", libro.getid_libro())
                            .setParameter("idAutor", autor.getIdAutor())
                            .executeUpdate();
                }
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction() != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    public void borrarTablaAutor() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("DROP TABLE IF EXISTS autor").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void borrarTablaLibro() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("DROP TABLE IF EXISTS libro").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void borrarTablaTema() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("DROP TABLE IF EXISTS tema").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void borrarTablaLibroAutor() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("DROP TABLE IF EXISTS libro_autor").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

}
