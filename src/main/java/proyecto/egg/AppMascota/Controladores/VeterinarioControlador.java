
package proyecto.egg.AppMascota.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import proyecto.egg.AppMascota.Entidades.Veterinario;

@Controller
public class VeterinarioControlador {
    @GetMapping("veterinario")
    public String veterinarioRegistro(Model model){
        Veterinario veterinario = new Veterinario ();
        model.addAttribute("veterinario", veterinario);
        return "veterinario";
 }   
    @PostMapping("veterinario")
    public String veterinarioExito (Model model){
        Veterinario veterinario = new Veterinario ();
        model.addAttribute("veterinario", veterinario); 
        return "exito";
}
    }
    
