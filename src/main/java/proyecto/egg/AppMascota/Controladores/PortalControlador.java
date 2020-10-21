
package proyecto.egg.AppMascota.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {
   @GetMapping ("/")
   public String index(){
   return ("index.html");
}
   @GetMapping ("/login")
  public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) { 
  if (error != null) {            
      model.put("error", "Mail o contraseña incorrectos.");         }    
  if (logout != null) {             
      model.put("error", "Salió correctamente de su sesión.");
  }         
  return "login.html"; 
  }

    
}
