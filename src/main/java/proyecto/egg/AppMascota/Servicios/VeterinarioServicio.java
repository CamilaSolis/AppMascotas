package proyecto.egg.AppMascota.Servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import proyecto.egg.AppMascota.Entidades.Veterinario;
import proyecto.egg.AppMascota.Errores.ErrorServicio;
import proyecto.egg.AppMascota.Repositorios.VeterinarioRepositorio;

@Service
public class VeterinarioServicio implements UserDetailsService {

    @Autowired
    VeterinarioRepositorio veterinarioRepositorio;

    public void registroVeterinario(String nombre, String matricula, String nombreClinica, String zona, String password1, String password2) throws ErrorServicio {
        validar(nombre,matricula,nombreClinica,zona,password1);

        Veterinario veterinario = new Veterinario();
        veterinario.setNombre(nombre);
        veterinario.setMatricula(matricula);
        veterinario.setNombreClinica(nombreClinica);
        veterinario.setZona(zona);

//        String encriptada = new BCryptPasswordEncoder().encode(password1);
//        veterinario.setPassword1(encriptada);
//        String encriptada2 = new BCryptPasswordEncoder().encode(password2);
//        veterinario.setPassword2(encriptada2);
        veterinarioRepositorio.save(veterinario);

    }

    public void bajaVeterinario(String matricula) throws ErrorServicio {
        Optional<Veterinario> res = veterinarioRepositorio.findById(matricula);
        if (res.isPresent()) {
            Veterinario veterinario = res.get();
            veterinario.setBaja(new Date());
            veterinarioRepositorio.save(veterinario);

        } else {
            throw new ErrorServicio("No se encontró esa Matricula de veterinario");
        }

    }

    public void modificacionVeterinario(String nombre, String matricula, String nombreClinica, String zona) throws ErrorServicio {
        Optional<Veterinario> res = veterinarioRepositorio.findById(matricula);

        if (res.isPresent()) {
            Veterinario veterinario = res.get();
            veterinario.setNombre(nombre);
            veterinario.setMatricula(matricula);
            veterinario.setNombreClinica(nombreClinica);
            veterinario.setZona(zona);

            veterinarioRepositorio.save(veterinario);

        } else {
            throw new ErrorServicio("No existe esa matricula de veterinario");
        }

    }

    public List<Veterinario> listarVeterinarios() {
        return veterinarioRepositorio.findAll();

    }

    public void buscarVeterinario(String matricula) {

        Optional<Veterinario> v = veterinarioRepositorio.findById(matricula);

        if (v.isPresent()) {
            Veterinario veterinario = v.get();
            veterinarioRepositorio.save(veterinario);

        } else {
            System.out.println("No existe esa Matricula en nuestra base de datos. Por favor ingreselo.");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
        Optional<Veterinario> veterinario = veterinarioRepositorio.findById(matricula);
        if (veterinario != null) {
            System.out.println(" matricula: " + veterinario.get().getMatricula() + " + clave " + veterinario.get().getPassword1());
            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_CLIENTE_REGISTRADO");
            permisos.add(p1);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("veterinarioSession", veterinario);

            User user = new User(veterinario.get().getMatricula(), veterinario.get().getPassword1(), permisos);
            return user;
        } else {
            return null;
        }
    }

    public void validar(String nombre, String matricula, String nombreClinica, String zona, String clave) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El documento no puede estar vacío");
        }
        if (matricula == null || matricula.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío");
        }
        if (nombreClinica == null || nombreClinica.isEmpty()) {
            throw new ErrorServicio("El telefono no puede estar vacío");
        }

        if (zona == null || zona.isEmpty()) {
            throw new ErrorServicio("El domicilio no puede estar vacío");
        }
        if (clave == null || clave.isEmpty()) {
            throw new ErrorServicio("El email no puede estar vacío");
//        }
//        if (!clave.equals(clave2)) {
//            throw new ErrorServicio("Las claves deben ser iguales");
//        } 
        }
    }

}
