package proyecto.egg.AppMascota.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyecto.egg.AppMascota.Errores.ErrorServicio;
import proyecto.egg.AppMascota.Servicios.MascotaServicio;



@Controller
@RequestMapping("/mascota")
public class MascotaControlador {

    @Autowired
    private MascotaServicio mascotaServicio;

    @GetMapping("")
    public String mascota(){
        return "crearMascota.html";
    }

    @PostMapping("/registroMascota")
    public String crearMascota(
            ModelMap model, 
            @RequestParam String nombre, 
            @RequestParam String raza, 
            @RequestParam String fechaNacimiento
            
    ) throws ErrorServicio {
        mascotaServicio.registroMascota(nombre, raza,fechaNacimiento );
              try{
        }catch (ErrorServicio ex){
            model.put("nombre", nombre);
            model.put("raza", raza);
            model.put("fecha nacimiento", fechaNacimiento);
            return "crearmascota.html";
        model.put("titulo", "Se creó la mascota");
        return "exito.html";
    }
    
    @GetMapping("/test")
    public String test(){
        return "exito.html";
    }

    }