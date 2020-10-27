/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.egg.AppMascota.Servicios;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import javax.transaction.Transactional;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import proyecto.egg.AppMascota.Entidades.Mascota;
import proyecto.egg.AppMascota.Errores.ErrorServicio;
import proyecto.egg.AppMascota.Repositorios.MascotaRepositorio;

/**
 *
 * @author Pablo
 */
@Service
public class MascotaServicio {

    @Autowired
    private MascotaRepositorio mascotaRepositorio;

    @Transactional
    public void registroMascota(String nombre, String raza, String fechaNacimiento) throws ErrorServicio {
//        validar(nombre, raza,fechaNacimiento);

       
        Mascota mascota;
        mascota = new Mascota();
        mascota.setNombre(nombre);
        mascota.setRaza(raza);
        mascota.setFechaNacimiento(fechaNacimiento);

        System.out.println(mascota.toString());
        
        if(false){
            throw new ErrorServicio("Comletar todos los campos");
        }
        
        mascotaRepositorio.save(mascota);
    }
    
    public void validation(String nombre, String raza, Date fechaNacimiento) throws ErrorServicio {
        
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("Completar nombre");
        }
        
          if (raza == null || raza.isEmpty()) {
            throw new ErrorServicio("Completar raza");
            
        } 
        if (fechaNacimiento == null || fechaNacimiento.isEmpty()) {
            throw new ErrorServicio("Completar fecha nacimiento");
        }
        
    }
    
       @Transactional
    public void modificaciónMascota(String nombre, String ID, String raza, Date fechaNacimiento) throws ErrorServicio{
        validar(nombre, raza, fechaNacimiento);
        Optional<Mascota> respuesta = MascotaRepositorio.findByName(nombre);
        if(respuesta.isPresent()){
            Mascota mascota = respuesta.get();
        mascota.setNombre(nombre);
        mascota.setRaza(raza);
        mascota.setFechaNacimiento(fechaNacimiento);
        mascotaRepositorio.save(mascota);
        }else{
            throw new ErrorServicio("La mascota no existe");
        }
    }
    
     public Mascota getMascota(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Mascota m = mascotaRepositorio.findByName(auth.getName());
        return m;
    }
    
    public Date convertirDate() {
        
        try {
            DateFormat fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd");
            Date convertido = fechaNacimiento.parse(fechaNacimiento);
            
            return convertido;
        } catch (ParseException ex) {
            Logger.getLogger(MascotaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
     
     
     
}





