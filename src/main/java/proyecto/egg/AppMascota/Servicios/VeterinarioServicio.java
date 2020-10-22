package proyecto.egg.AppMascota.Servicios;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import proyecto.egg.AppMascota.Entidades.Veterinario;
import proyecto.egg.AppMascota.Errores.ErrorServicio;
import proyecto.egg.AppMascota.Repositorios.VeterinarioRepositorio;
@Service
public class VeterinarioServicio {

    @Autowired
    VeterinarioRepositorio veterinarioRepositorio;

    public void registroVeterinario(String nombre, String matricula, String nombreClinica, String zona, String password1, String password2)throws ErrorServicio {
        validar(nombre);
        Veterinario veterinario = new Veterinario();
        veterinario.setNombre(nombre);
        veterinario.setMatricula(matricula);
        veterinario.setNombreClinica(nombreClinica);
        veterinario.setZona(zona);

//        String encriptada = new BCryptPasswordEncoder().encode(password1);
//        veterinario.setPassword1(encriptada);
//        String encriptada2 = new BCryptPasswordEncoder().encode(password2);
//        veterinario.setPassword2(encriptada2);
        
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

    public void validar(String nombre)throws ErrorServicio{
         if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío");
        }
    }
}
