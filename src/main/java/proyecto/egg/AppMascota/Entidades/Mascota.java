
package proyecto.egg.AppMascota.Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author USUARIO1
 */
@Entity
public class Mascota implements Serializable {
    private String nombre;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String raza;
    private String fechaNacimiento;
    @OneToMany
    private List<Consulta> historiaClinica;
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Consulta> getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(List<Consulta> historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    @Override
    public String toString() {
        return "Mascota{" + "nombre=" + nombre + ", id=" + id + ", raza=" + raza + ", fechaNacimiento=" + fechaNacimiento + ", historiaClinica=" + historiaClinica + '}';
    }

   
    
    
}
