package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuLecturaliaJPA {
    private int op;

    /**
     * Constructor de la clase LecturaliaMenu.
     */
    public MenuLecturaliaJPA(){
        super();
    }

    /**
     * Muestra el menú principal y devuelve la opción seleccionada por el usuario.
     *
     * @return La opción seleccionada por el usuario.
     */
    public int menuPrincipal(){
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.println("✎✧˚ ༘ ⋆｡˚Bienvenido a la Biblioteca Digital de Lecturalia ✎✧˚ ༘ ⋆｡˚\n");
            System.out.println("Escoja una de las siguientes opciones: \n");
            System.out.println("˚ ༘ ⋆｡CONSULTA˚ ༘ ⋆｡\n");
            System.out.println("1. Mostrar todos los libros");
            System.out.println("2. Mostrar todos los autores");
            System.out.println("3. Mostrar todos los temas");
            System.out.println("4. Mostrar libros y autores a la vez en la tabla conjunta");
            System.out.println("5. Mostrar libros de un autor en concreto");
            System.out.println("6. Mostrar los autores de un libro\n \n");
            System.out.println("˚ ༘ ⋆｡MODIFICACIÓN/ELIMINACIÓN˚ ༘ ⋆｡\n");
            System.out.println("7. Añadir un libro nuevo a la Biblioteca");
            System.out.println("8. Añadir un autor nuevo a la Biblioteca");
            System.out.println("9. Añadir un tema nuevo a la Biblioteca");
            System.out.println("10. Escoger un libro y modificar su información");
            System.out.println("11. Escoger un autor y modificar su información");
            System.out.println("12. Escoger un tema y modificar su información");
            System.out.println("13. Eliminar información de un libro");
            System.out.println("14. Eliminar información de un autor");
            System.out.println("15. Eliminar información de un tema\n \n");
            System.out.println("˚ ༘ ⋆｡RESTABLECIMIENTO˚ ༘ ⋆｡\n");
            System.out.println("16. Poblar las tablas con datos");
            System.out.println("17. Borrar tablas de la Biblioteca Digital");
            System.out.println("18. Crear tablas de la Biblioteca Digital\n \n");
            System.out.println("19. Salir");
            try {
                op = Integer.parseInt(lector.readLine());
            } catch (IOException e) {
                System.out.println("Escoja el número correspondiente a la operación");
                e.printStackTrace();
            }

        }
        while (op < 1 || op > 12);
        return op;
    }
}
