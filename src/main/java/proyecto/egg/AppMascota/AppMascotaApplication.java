package proyecto.egg.AppMascota;

import java.util.List;
import java.util.Optional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import proyecto.egg.AppMascota.Entidades.Consulta;
import proyecto.egg.AppMascota.Entidades.Mascota;
import proyecto.egg.AppMascota.Entidades.Veterinario;
import proyecto.egg.AppMascota.Errores.ErrorServicio;
import proyecto.egg.AppMascota.Repositorios.MascotaRepositorio;
import proyecto.egg.AppMascota.Servicios.MascotaServicio;
import proyecto.egg.AppMascota.Servicios.VeterinarioServicio;

@SpringBootApplication
public class AppMascotaApplication {

	public static void main(String[] args) throws ErrorServicio {
		SpringApplication.run(AppMascotaApplication.class, args);
        }
 
}
