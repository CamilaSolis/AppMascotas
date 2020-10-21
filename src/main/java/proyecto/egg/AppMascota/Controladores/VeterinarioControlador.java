
package proyecto.egg.AppMascota.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import proyecto.egg.AppMascota.Entidades.Veterinario;

@Controller
@RequestMapping("/veterinario")
public class VeterinarioControlador {
      
   @GetMapping ("/registroVeterinario")
   public String registro(){
   return ("veterinario.html");
    }
    
    
//    @GetMapping("veterinario")
//    public String veterinarioRegistro(Model model){
//        Veterinario veterinario = new Veterinario ();
//        model.addAttribute("veterinario", veterinario);
//        return "veterinario.html";
// }   
//
//   
//    @PostMapping("veterinario")
//    public String veterinarioExito (Model model){
//        Veterinario veterinario = new Veterinario ();
//        model.addAttribute("veterinario", veterinario); 
//        return "exito";
//}
    }
    
