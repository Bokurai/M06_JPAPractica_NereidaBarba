package Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Access(AccessType.FIELD)
@Table(name = "autor")
public class Autor implements Serializable {

    @Id
    @Column(name = "nombre")
    String autor_nombre;

    @Column(name = "fecha_de_nacimiento")
    String fecha_nacimiento;

    @Column(name = "genero")
    String genero;

    @Column(name = "ciudad_nacimiento")
    String ciudad;

    @Column(name = "pais")
    String pais;
}
