package proyecto.egg.AppMascota.Controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        return "exitov.html";
    }
    
        @PostMapping("/modificarVeterinario")
    public String modificarVeterinario(ModelMap model, @RequestParam String nombre, @RequestParam String matricula, @RequestParam String nombreClinica,
            @RequestParam String zona) throws ErrorServicio {

        try {
            veterinarioServicio.modificacionVeterinario(nombre, matricula, nombreClinica, zona);
        } catch (ErrorServicio ex) {
            model.put("error", ex.getMessage());
            model.put("nombre", nombre);
            return "veterinario.html";
        }
        model.put("titulo", "Se modificó el veterinario");
        return "exitov.html";
    }

    @GetMapping("/crearConsulta")
    public String crear_consulta(ModelMap model, @RequestParam String documento) {
        List<Mascota> mascotas = mascotaRepositorio.listarMascotasPorCliente(documento);
        model.put("mascotas", mascotas);
        return "crearConsulta.html";
    }
    
    @PostMapping("/crearConsultaPost")
    public String crearConsultaPost(ModelMap model, @RequestParam String documento) {
        List<Mascota> mascotas = mascotaRepositorio.listarMascotasPorCliente(documento);
        model.put("mascotas", mascotas);
        return "crearConsulta.html";
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
 
       
        return "exitov.html";
    }

    @PostMapping("/buscarClientePost")
    public String buscarClientePost(@RequestParam String documento, ModelMap model) throws ErrorServicio {
//        List<Mascota> mascotas = mascotaRepositorio.listarMascotasPorCliente(documento);
//        model.put("mascotas", mascotas);
        Optional<Cliente> cliente = clienteRepositorio.findById(documento);
        if (cliente.isPresent()){
            List<Cliente> clientes = new ArrayList();
            clientes.add(cliente.get());
            model.put("clientes", clientes);
            model.put("documento",documento);
        }else{
            throw new ErrorServicio("No se encontro el cliente");
        }
        
        return "buscarCliente.html";
    }
    
    @GetMapping("/buscarCliente")
    public String buscarCliente(ModelMap model){
        List<Cliente> clientes = clienteRepositorio.buscarClientes();
        model.put("clientes",clientes);
        
        return "buscarCliente.html";
    }
    
  @GetMapping("/listarVeterinarios")
    public String listarVeterinarios(ModelMap model){

        List<Veterinario> veterinarios = veterinarioServicio.listarVeterinarios();
        model.put("veterinarios", veterinarios);
        return "listarVeterinarios.html";
    }
       
    
    
    @PostMapping("/buscarMascotas")
    public String buscarMascotas(ModelMap model, @RequestParam String documento){
//        String documento = cliente.getDocumento();
        List<Mascota> mascotas = mascotaRepositorio.listarMascotasPorCliente(documento);
        model.put("mascotas", mascotas);
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
