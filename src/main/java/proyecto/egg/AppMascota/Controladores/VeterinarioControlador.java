package proyecto.egg.AppMascota.Controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyecto.egg.AppMascota.Entidades.Cliente;
import proyecto.egg.AppMascota.Entidades.Mascota;
import proyecto.egg.AppMascota.Entidades.Veterinario;
import proyecto.egg.AppMascota.Errores.ErrorServicio;
import proyecto.egg.AppMascota.Repositorios.ClienteRepositorio;
import proyecto.egg.AppMascota.Repositorios.MascotaRepositorio;
import proyecto.egg.AppMascota.Servicios.ConsultaServicio;
import proyecto.egg.AppMascota.Servicios.VeterinarioServicio;

@Controller
@RequestMapping("/veterinario")
public class VeterinarioControlador {

    @Autowired
    private VeterinarioServicio veterinarioServicio;
    @Autowired
    private ConsultaServicio consultaServicio;
    @Autowired
    private MascotaRepositorio mascotaRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @GetMapping("")
    public String veterinario() {
        return "veterinario.html";
    }

    @PostMapping("/registroVeterinario")
    public String registroVeterinario(ModelMap model, @RequestParam String nombre, @RequestParam String matricula, @RequestParam String nombreClinica,
            @RequestParam String zona, @RequestParam String password1, @RequestParam String password2) throws ErrorServicio {

        try {
            veterinarioServicio.registroVeterinario(nombre, matricula, nombreClinica, zona, password1, password2);
        } catch (ErrorServicio ex) {
            model.put("error", ex.getMessage());
            model.put("nombre", nombre);
            return "veterinario.html";
        }
        model.put("titulo", "Se creó el veterinario");
        return "exito";
    }

    @PostMapping("/crear_consulta")
    public String crearConsulta(ModelMap model, @RequestParam Integer precio, @RequestParam String matriculaVeterinario,
            @RequestParam String nombreMascota, @RequestParam String peso, @RequestParam String motivo,
            @RequestParam String observaciones) throws ErrorServicio {
        try {
            List<Cliente> clientes = clienteRepositorio.buscarClientes();
            model.put("clientes", clientes);

            consultaServicio.registrar(motivo, precio, peso, observaciones, matriculaVeterinario, nombreMascota);

        } catch (ErrorServicio ex) {
            model.put("error", ex.getMessage());
            model.put("motivo", motivo);
            model.put("precio", precio);
            model.put("peso", peso);
            model.put("observaciones", observaciones);
            model.put("matriculaVeterinario", matriculaVeterinario);

            model.put("nombreMascota", nombreMascota);

            return "crearConsulta.html";
        }
        model.put("titulo", "Se creó la consulta!");

        return "exito.html";
    }

    @PostMapping("/buscarCliente")
    public String buscarCliente(@RequestParam String documento, ModelMap model) {
        List<Mascota> mascotas = mascotaRepositorio.listarMascotasPorCliente(documento);
        model.put("mascotas", mascotas);
        model.put("documento", documento);
        return "crearConsulta.html";
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
