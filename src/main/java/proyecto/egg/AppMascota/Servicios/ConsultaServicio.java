
package proyecto.egg.AppMascota.Servicios;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import proyecto.egg.AppMascota.Entidades.Consulta;
import proyecto.egg.AppMascota.Entidades.Veterinario;
import proyecto.egg.AppMascota.Errores.ErrorServicio;
import proyecto.egg.AppMascota.Repositorios.ConsultaRepositorio;
import proyecto.egg.AppMascota.Repositorios.VeterinarioRepositorio;

@Service
public class ConsultaServicio implements UserDetailsService{

    @Autowired
    private ConsultaRepositorio consultaRepositorio;
    
    @Autowired
    private VeterinarioRepositorio veterinarioRepositorio;
//    
//    @Autowired
//    private MascotaRepositorio mascotaRepositorio;
    
    @Transactional
    public void registrar(String motivo,Integer precio, String peso, String vacuna, String cirujia, String observaciones, String idVeterinario, String idMascota) throws ErrorServicio{
        validar(motivo,precio,peso,vacuna,cirujia,observaciones);
        
        Consulta consulta = new Consulta();
        
        Date fecha = Calendar.getInstance().getTime();
        consulta.setFecha(fecha);
        
        consulta.setMotivo(motivo);
        consulta.setPrecio(precio);
        consulta.setPrecio(precio);
        consulta.setVacuna(vacuna);
        consulta.setCirujia(cirujia);
        consulta.setObservaciones(observaciones);
        
        Optional<Veterinario> buscarVeterinario = veterinarioRepositorio.findById(idVeterinario);      
        if (buscarVeterinario.isPresent()){
          Veterinario veterinario = buscarVeterinario.get();
          consulta.setVeterinario(veterinario);
          }else{
            throw new ErrorServicio("No se encontro el veterinario solicitado");
        }

//        Optional<Mascota> buscarMascota = mascotaRepositorio.findById(idVeterinario);      
//        if (buscarMascota.isPresent()){
//          Mascota mascota = buscarMascota.get();
//          consulta.setMascota(mascota);
//          }else{
//            throw new ErrorServicio("No se encontro la mascota solicitada");
//        }

        consultaRepositorio.save(consulta);
    }
    
    @Transactional
    public void modificar(String id,String motivo,Integer precio, String peso, String vacuna, String cirujia, String observaciones, String idVeterinario, String idMascota) throws ErrorServicio{
        validar(motivo,precio,peso,vacuna,cirujia,observaciones);
        
        Optional<Consulta> buscarConsulta = consultaRepositorio.findById(id);
        
        if(buscarConsulta.isPresent()){
            Consulta consulta = buscarConsulta.get();
            consulta.setMotivo(motivo);
            consulta.setPrecio(precio);
            consulta.setVacuna(vacuna);
            consulta.setCirujia(cirujia);
            consulta.setObservaciones(observaciones);
            
        Optional<Veterinario> buscarVeterinario = veterinarioRepositorio.findById(idVeterinario);      
        if (buscarVeterinario.isPresent()){
          Veterinario veterinario = buscarVeterinario.get();
          consulta.setVeterinario(veterinario);
          }else{
            throw new ErrorServicio("No se encontro el veterinario solicitado");
        }

//        Opcional<Mascota> buscarMascota = mascotaRepositorio.findById(idVeterinario);      
//        if (buscarMascota.isPresent()){
//          Mascota mascota = buscarMascota.get();
//          consulta.setMascota(mascota);
//          }else{
//            throw new ErrorServicio("No se encontro la mascota solicitada");
//        }
            consultaRepositorio.save(consulta);
        }else{
            throw new ErrorServicio("No se encontro la consulta solicitada");
        }
        
    }
  
    
    private void validar(String motivo,Integer precio, String peso, String vacuna, String cirujia, String observaciones) throws ErrorServicio{
        if (motivo == null || motivo.isEmpty()){
            throw new ErrorServicio("El motivo no puede ser nulo");
        }
        if (precio == null){
            throw new ErrorServicio("El precio no puede ser nulo");
        }
        if (peso == null || peso.isEmpty()){
            throw new ErrorServicio("El peso no puede ser nulo");
        }if (vacuna == null || vacuna.isEmpty()){
            throw new ErrorServicio("La vacuna no puede ser nula");
        }
        if (cirujia == null || cirujia.isEmpty()){
            throw new ErrorServicio("La cirujia no puede ser nula");
        }
        if (observaciones == null || observaciones.isEmpty()){
            throw new ErrorServicio("Las observaciones no pueden ser nulas");
        }
        
        
    }
    
    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
