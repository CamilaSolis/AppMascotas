package proyecto.egg.AppMascota.Servicios;

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
        
    }
    
    public void bajaVeterinario(String matricula) {
        
    
    
    }
    
    public void modificacionVeterinario(String nombre, String matricula, String nombreClinica, String zona){
        
    }
    
    public void buscarVeterinario(String id){
        
    }

}
