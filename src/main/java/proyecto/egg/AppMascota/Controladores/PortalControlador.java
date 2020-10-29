package proyecto.egg.AppMascota.Controladores;

import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyecto.egg.AppMascota.Servicios.ClienteServicio;
import proyecto.egg.AppMascota.Servicios.VeterinarioServicio;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private VeterinarioServicio veterinarioServicio;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
        if (error != null) {
            model.put("error", "Mail o contraseña incorrectos.");
        }
        if (logout != null) {
            model.put("error", "Salió correctamente de su sesión.");
        }
        return "login.html";
    }

    @GetMapping("/vetlogin")
    public String vetlogin(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
        if (error != null) {
            model.put("error", "Matrícula o contraseña incorrectos.");
        }
        if (logout != null) {
            model.put("error", "Salió correctamente de su sesión.");
        }
        return "vetlogin.html";
    }

    @PreAuthorize("hasRole('ROLE_CLIENTE_REGISTRADO')")
    @GetMapping("/opciones_cliente")
    public String panelUsuario(HttpSession session, ModelMap model) {

        model.put("clientesession", clienteServicio.getCliente());
        return "opciones_cliente.html";

    }

    @GetMapping("/middleware")
    public String middleware() {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().toArray()[0].toString().equals("ROLE_CLIENTE_REGISTRADO")) {

            return "redirect:/opciones_cliente";
        } else {

            return "redirect:/opciones_veterinario";
        }
    }

    @PreAuthorize("hasRole('ROLE_VETERINARIO_REGISTRADO')")
    @GetMapping("/opciones_veterinario")
    public String panelVeterinario(HttpSession session, ModelMap model) {

        return "opciones_veterinario.html";
    }
    
    @GetMapping("/crearConsulta")
    public String crear_consulta() {
        return "crearConsulta.html";
    }
}
