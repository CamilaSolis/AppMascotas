package proyecto.egg.AppMascota.Servicios;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import proyecto.egg.AppMascota.Entidades.Veterinario;
import proyecto.egg.AppMascota.Repositorios.VeterinarioRepositorio;

public class VeterinarioServicio {

    @Autowired
    VeterinarioRepositorio veterinarioRepositorio;

    public void registroVeterinario(String nombre, String matricula, String nombreClinica, String zona) {
        Veterinario veterinario = new Veterinario();
        veterinario.setNombre(nombre);
        veterinario.setMatricula(matricula);
        veterinario.setNombreClinica(nombreClinica);
        veterinario.setZona(zona);

        veterinarioRepositorio.save(veterinario);

    }

    public void bajaVeterinario(String matricula) {

    }

    public void modificacionVeterinario(String nombre, String matricula, String nombreClinica, String zona) {

    }

    public void buscarVeterinario(String matricula) {
        
        Optional<Veterinario> v = veterinarioRepositorio.findById(matricula);
        
        if (v.isPresent()){
            Veterinario veterinario = v.get();
            veterinarioRepositorio.save(veterinario);
            
            
        }
        else{
            System.out.println("No existe esa Matricula en nuestra base de datos. Por favor ingreselo.");
        }
        

    }

}
