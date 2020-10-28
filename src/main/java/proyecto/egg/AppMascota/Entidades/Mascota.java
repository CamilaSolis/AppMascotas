
package proyecto.egg.AppMascota.Entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Mascota {
    private String nombre;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String raza;
    private Date fechaNacimiento;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
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
