
package proyecto.egg.AppMascota.Servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import proyecto.egg.AppMascota.Entidades.Cliente;
import proyecto.egg.AppMascota.Errores.ErrorServicio;
import proyecto.egg.AppMascota.Repositorios.ClienteRepositorio;

@Service
public class ClienteServicio {
    
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    
    @Transactional
    public void registroCliente(String nombre, String documento, String telefono, String email,String domicilio) throws ErrorServicio{
        validar(nombre, documento,telefono, email, domicilio);
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setDocumento(documento);
        cliente.setTelefono(telefono);
        cliente.setEmail(email);
        cliente.setDomicilio(domicilio);
        
        clienteRepositorio.save(cliente);
    }
    
    @Transactional
    public void bajaCliente(String documento) throws ErrorServicio{
        Optional<Cliente> respuesta = clienteRepositorio.findById(documento);
        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();
            cliente.setBaja(new Date());
            clienteRepositorio.save(cliente);
        } else {
            throw new ErrorServicio("No se encontró un cliente con ese id");
        }
    }
    
    @Transactional
    public void modificaciónCliente(String nombre, String documento, String telefono, String email,String domicilio) throws ErrorServicio{
        validar(nombre, documento,telefono, email, domicilio);
        Optional<Cliente> respuesta = clienteRepositorio.findById(documento);
        if(respuesta.isPresent()){
            Cliente cliente = respuesta.get();
        cliente.setNombre(nombre);
        cliente.setDocumento(documento);
        cliente.setTelefono(telefono);
        cliente.setEmail(email);
        cliente.setDomicilio(domicilio);
        
        clienteRepositorio.save(cliente);
        }else{
            throw new ErrorServicio("No se encontró el cliente");
        }
    }
    
    public void validar(String nombre, String documento, String telefono, String email, String domicilio) throws ErrorServicio {
        
        if (documento == null || documento.isEmpty()) {
            throw new ErrorServicio("El documento no puede estar vacío");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío");
        }
          if (telefono == null || telefono.isEmpty()) {
            throw new ErrorServicio("El telefono no puede estar vacío");
        }
        
        if (domicilio == null || domicilio.isEmpty()) {
            throw new ErrorServicio("El domicilio no puede estar vacío");
        }
        if (email == null || email.isEmpty()) {
            throw new ErrorServicio("El email no puede estar vacío");
//        }
//        if (!clave.equals(clave2)) {
//            throw new ErrorServicio("Las claves deben ser iguales");
//        }
        }
    }
    
     public Cliente getCliente(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Cliente c = clienteRepositorio.buscarPorMail(auth.getName());
        return c;
    }
    
    @Override
    public UserDetails loadUserByUsername(String documento) throws UsernameNotFoundException {
        Cliente cliente = clienteRepositorio.findById(documento);
        if (cliente != null) {
            System.out.println(" mail: " + cliente.getEmail()) + " + clave " + cliente.getClave());
            List<GrantedAuthority> permisos = new ArrayList<>();
            
            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_CLIENTE_REGISTRADO");
            permisos.add(p1);
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("clientesession", cliente);
            
            User user = new User(cliente.getEmail(), cliente.getClave(), permisos);
            return user;
        } else {
            return null;
        }
    }
    
    
}
