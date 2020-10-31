package proyecto.egg.AppMascota;

import java.util.List;
import java.util.Optional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import proyecto.egg.AppMascota.Entidades.Consulta;
import proyecto.egg.AppMascota.Entidades.Mascota;
import proyecto.egg.AppMascota.Errores.ErrorServicio;
import proyecto.egg.AppMascota.Repositorios.MascotaRepositorio;
import proyecto.egg.AppMascota.Servicios.MascotaServicio;

@SpringBootApplication
public class AppMascotaApplication {

	public static void main(String[] args) throws ErrorServicio {
		SpringApplication.run(AppMascotaApplication.class, args);
                
                
                MascotaServicio mascotaServicio = new MascotaServicio();
//                
//                List<Consulta> consultas = mascotaServicio.historia_clinica("005350a4-1739-4a9a-8f5c-9237b93c8d27");
//                Consulta consulta = consultas.get(0);
//                System.out.println(consulta.getId());


                   Mascota mascota = mascotaServicio.buscarMascota("1");
                   System.out.println(mascota.getNombre());
        
        }
 
}
