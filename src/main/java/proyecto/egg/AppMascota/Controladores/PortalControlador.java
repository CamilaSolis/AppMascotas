
package proyecto.egg.AppMascota.Controladores;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyecto.egg.AppMascota.Servicios.ClienteServicio;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    private ClienteServicio clienteServicio;
    
    @GetMapping ("/")
    public String index(){
        return "index.html";
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

    @PreAuthorize("hasRole('ROLE_CLIENTE_REGISTRADO')")
    @GetMapping("/panelUsuario")
    public String panelUsuario(HttpSession session, ModelMap model ) {
        
        System.out.println(clienteServicio.getCliente().getEmail());
        
        model.put("clientesession",clienteServicio.getCliente());
        
        return "panelUsuario.html";
    }
  }

    

