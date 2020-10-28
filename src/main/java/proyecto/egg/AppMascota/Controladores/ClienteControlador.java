
package proyecto.egg.AppMascota.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyecto.egg.AppMascota.Errores.ErrorServicio;
import proyecto.egg.AppMascota.Servicios.ClienteServicio;

@Controller
@RequestMapping("/cliente")
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("")
    public String cliente(){
        return "registroCliente.html";
    }

    @PostMapping("/registroCliente")
    public String registroCliente(ModelMap model, @RequestParam String nombre, @RequestParam String documento, @RequestParam String telefono,
            @RequestParam String email, @RequestParam String domicilio, @RequestParam String clave1, @RequestParam String clave2) throws ErrorServicio {

        try {
            clienteServicio.registroCliente(nombre, documento, telefono, email, domicilio, clave1, clave2);
        } catch (ErrorServicio ex) {
            model.put("error", ex.getMessage());
            model.put("nombre", nombre);
            model.put("documento", documento);
            model.put("telefono", telefono);
            model.put("email", email);
            model.put("domicilio", domicilio);
            return "registroCliente.html";
        }
        model.put("titulo", "Se creó el cliente");
        return "exito";
    }
    
    
}

