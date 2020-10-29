package proyecto.egg.AppMascota.Controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyecto.egg.AppMascota.Entidades.Cliente;
import proyecto.egg.AppMascota.Entidades.Mascota;
import proyecto.egg.AppMascota.Errores.ErrorServicio;
import proyecto.egg.AppMascota.Servicios.ClienteServicio;
import proyecto.egg.AppMascota.Servicios.MascotaServicio;

@Controller
@RequestMapping("/mascota")
public class MascotaControlador {

    @Autowired
    private MascotaServicio mascotaServicio;
    
    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("/crearMascota")
    public String mascota() {
        return "crearMascota.html";
    }

    @PostMapping("/registroMascota")
    public String crearMascota(
            ModelMap model,
            @RequestParam String nombre,
            @RequestParam String raza,
            @RequestParam String sexo
    ) throws ErrorServicio {

        try {
            
            mascotaServicio.registroMascota(nombre, raza, sexo );
        } catch (ErrorServicio ex) {
            model.put("nombre", nombre);
            model.put("raza", raza);
            model.put("sexo", sexo);
            return "crearmascota.html";
        }
        model.put("titulo", "Se creó la mascota");
        return "exito.html";
    }

    @GetMapping("/listarMascotas")
    public String listarMascotasPorCliente(String documento, ModelMap model) {
        
        List<Mascota> mascotas = mascotaServicio.listarMascotasPorCliente(documento);
        model.put("mascotas", mascotas);
             
        return "crearMascota.html";
    }

}
