package Model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Access(AccessType.FIELD)
@Table(name = "autor")
public class Autor implements Serializable {
}
