package Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Access(AccessType.FIELD)
@Table(name = "temas")
public class Tema implements Serializable {

    @Id
    @Column(name = "nombre_tema")
    String nombre_tema;

    public Tema(String nombre_tema) {
        this.nombre_tema = nombre_tema;
    }
    public Tema() {
    }

    public String getNombre_tema() {
        return nombre_tema;
    }

    public void setNombre_tema(String nombre_tema) {
        this.nombre_tema = nombre_tema;
    }

    @Override
    public String toString() {
        return "Tema{" +
                "nombre_tema='" + nombre_tema + '\'' +
                '}';
    }

}
