/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.egg.AppMascota.Servicios;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.egg.AppMascota.Entidades.Consulta;
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
    public void crearMascota(String nombre, String raza, String fechaNacimiento) throws ErrorServicio {
//        validar(nombre, raza,fechaNacimiento);

        Date fecha = new Date();
        Mascota mascota = new Mascota();
        mascota.setNombre(nombre);
        mascota.setRaza(raza);
        mascota.setFechaNacimiento(fecha);

        System.out.println(mascota.toString());
        
        if(false){
            throw new ErrorServicio("asd");
        }
        
        mascotaRepositorio.save(mascota);
    }
    public List<Consulta> historia_clinica(String Id) throws ErrorServicio{
        Optional<Mascota> mascota = mascotaRepositorio.findById(Id);
        
        if (mascota.isPresent()){
            List<Consulta> consultas = mascota.get().getHistoriaClinica();
            return consultas;
        }else{
            throw new ErrorServicio("No se encontro la lista de consultas");
        } 
    }

}


