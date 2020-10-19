
package proyecto.egg.AppMascota.Entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Consulta {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Date fecha;
    private String motivo;
    private Integer precio;
    private String peso;
    private String vacuna;
    private String cirujia;
    private String observaciones;
    
    @OneToOne
    private Veterinario veterinario;
    @ManyToOne
    private Mascota mascota;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getVacuna() {
        return vacuna;
    }

    public void setVacuna(String vacuna) {
        this.vacuna = vacuna;
    }

    public String getCirujia() {
        return cirujia;
    }

    public void setCirujia(String cirujia) {
        this.cirujia = cirujia;
    }

    @Override
    public String toString() {
        return "Consulta{" + "id=" + id + ", fecha=" + fecha + ", motivo=" + motivo + ", precio=" + precio + ", peso=" + peso + ", vacuna=" + vacuna + ", cirujia=" + cirujia + ", observaciones=" + observaciones + ", veterinario=" + veterinario + ", mascota=" + mascota + '}';
    }
    
    
}
