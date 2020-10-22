
package proyecto.egg.AppMascota.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyecto.egg.AppMascota.Entidades.Veterinario;
import proyecto.egg.AppMascota.Errores.ErrorServicio;
import proyecto.egg.AppMascota.Servicios.VeterinarioServicio;


@Controller
@RequestMapping("/veterinario")
public class VeterinarioControlador {
      
    @Autowired
    private VeterinarioServicio veterinarioServicio;
    
    @GetMapping("")
    public String veterinario(){
        return "veterinario.html";
    }
  
    
    @PostMapping("/registroVeterinario")
    public String registroVeterinario (ModelMap model , ModelMap modelo, @RequestParam String nombre,@RequestParam String matricula,@RequestParam String nombreClinica, 
        @RequestParam String zona, @RequestParam String password1, @RequestParam String password2) throws ErrorServicio{
       
        try{
            veterinarioServicio.registroVeterinario(nombre, matricula, nombreClinica, zona, password1,password2);
        } catch (ErrorServicio ex){
            model.put("error", ex.getMessage());
            model.put("nombre", nombre);
            return "veterinario.html";
        }
        model.put("titulo", "Se creó el veterinario");
        return "exito";
}
    
    
//    @GetMapping("veterinario")
//    public String veterinarioRegistro(Model model){
//        Veterinario veterinario = new Veterinario ();
//        model.addAttribute("veterinario", veterinario);
//        return "veterinario.html";
// }   
//
//   
   
    }
    
