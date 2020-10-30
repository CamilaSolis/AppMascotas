package proyecto.egg.AppMascota.Servicios;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import proyecto.egg.AppMascota.Entidades.Consulta;
import proyecto.egg.AppMascota.Entidades.Mascota;
import proyecto.egg.AppMascota.Entidades.Veterinario;
import proyecto.egg.AppMascota.Errores.ErrorServicio;
import proyecto.egg.AppMascota.Repositorios.ConsultaRepositorio;
import proyecto.egg.AppMascota.Repositorios.MascotaRepositorio;
import proyecto.egg.AppMascota.Repositorios.VeterinarioRepositorio;

@Service
public class ConsultaServicio implements UserDetailsService {

    @Autowired
    private ConsultaRepositorio consultaRepositorio;

    @Autowired
    private VeterinarioRepositorio veterinarioRepositorio;

    @Autowired
    private MascotaRepositorio mascotaRepositorio;
     @Autowired
    private MascotaServicio mascotaServicio;

    @Transactional
    public void registrar(String motivo, Integer precio, String peso, String observaciones, String matriculaVeterinario, String nombreMascota) throws ErrorServicio {
        validar(motivo, precio, peso, observaciones);
        Mascota mascota = mascotaRepositorio.buscarMascotaPorNombre(nombreMascota);
        
        Consulta consulta;
        consulta = new Consulta();
        Date fecha = Calendar.getInstance().getTime();
        
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatter.format(fecha);
        
        consulta.setFecha(date);
        
        consulta.setMotivo(motivo);
        consulta.setPrecio(precio);
        consulta.setPeso(peso);
        consulta.setObservaciones(observaciones);
        
        System.out.println(consulta.toString());
        Optional<Veterinario> buscarVeterinario = veterinarioRepositorio.findById(matriculaVeterinario);
        if (buscarVeterinario.isPresent()) {
            Veterinario veterinario = buscarVeterinario.get();
            consulta.setVeterinario(veterinario);
        } else {
            throw new ErrorServicio("No se encontro el veterinario solicitado");
        }
        consultaRepositorio.save(consulta);
        System.out.println("Guardó la consulta");
        
        List<Consulta> listaConsultas = new ArrayList();
        try {
            listaConsultas = mascota.getHistoriaClinica();
            
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("+++++++++++++++++++++++++++++" + "error en la lista consultas");
        }
        
        System.out.println("Trajo la lista");
        listaConsultas.add(consulta);
        System.out.println("guardó la lista");
        mascota.setHistoriaClinica(listaConsultas);
        System.out.println("guardó la mascota");
        mascotaRepositorio.save(mascota);
    }
        
//        Mascota mascota = mascotaRepositorio.buscarMascotaPorNombre(nombreMascota);
//        Mascota mascota = mascotaRepositorio.buscarPorId(idMascota);
//        if (mascota != null) {
//            consulta.setMascota(mascota);
//        } else {
//            throw new ErrorServicio("No se encontro la mascota solicitada");
//        }
        
    

    @Transactional
    public void modificar(String id, String motivo, Integer precio, String peso, String vacuna, String cirujia, String observaciones, String idVeterinario, String idMascota) throws ErrorServicio {
        validar(motivo, precio, peso, observaciones);

        Optional<Consulta> buscarConsulta = consultaRepositorio.findById(id);

        if (buscarConsulta.isPresent()) {
            Consulta consulta = buscarConsulta.get();
            consulta.setMotivo(motivo);
            consulta.setPrecio(precio);
            consulta.setObservaciones(observaciones);

            Optional<Veterinario> buscarVeterinario = veterinarioRepositorio.findById(idVeterinario);
            if (buscarVeterinario.isPresent()) {
                Veterinario veterinario = buscarVeterinario.get();
                consulta.setVeterinario(veterinario);
            } else {
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
        } else {
            throw new ErrorServicio("No se encontro la consulta solicitada");
        }

    }

    private void validar(String motivo, Integer precio, String peso, String observaciones) throws ErrorServicio {
        if (motivo == null || motivo.isEmpty()) {
            throw new ErrorServicio("El motivo no puede ser nulo");
        }
        if (precio == null) {
            throw new ErrorServicio("El precio no puede ser nulo");
        }
        if (peso == null || peso.isEmpty()) {
            throw new ErrorServicio("El peso no puede ser nulo");
        }
        if (observaciones == null || observaciones.isEmpty()) {
            throw new ErrorServicio("Las observaciones no pueden ser nulas");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

