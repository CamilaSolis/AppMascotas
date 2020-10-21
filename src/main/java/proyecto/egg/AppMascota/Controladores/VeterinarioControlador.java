
package proyecto.egg.AppMascota.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyecto.egg.AppMascota.Entidades.Veterinario;

@Controller
@RequestMapping("/veterinario")
public class VeterinarioControlador {
      
   @GetMapping ("/registroVeterinario")
   public String registro(){
   return "Veterinario.html";
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
//    public String veterinarioExito (Model model , ModelMap modelo, @RequestParam String nombre,@RequestParam String matricula,@RequestParam String nombreClinica, @RequestParam String zona){
//        Veterinario veterinario = new Veterinario ();
//        model.addAttribute("veterinario", veterinario); 
//        return "exito";
//}
    }
    
