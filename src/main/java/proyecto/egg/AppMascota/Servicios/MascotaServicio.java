/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.egg.AppMascota.Servicios;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import proyecto.egg.AppMascota.Entidades.Cliente;
import proyecto.egg.AppMascota.Entidades.Consulta;
import proyecto.egg.AppMascota.Entidades.Mascota;
import proyecto.egg.AppMascota.Errores.ErrorServicio;
import proyecto.egg.AppMascota.Repositorios.ClienteRepositorio;
import proyecto.egg.AppMascota.Repositorios.MascotaRepositorio;

/**
 *
 * @author Pablo
 */
@Service
public class MascotaServicio {

    @Autowired
    private MascotaRepositorio mascotaRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Transactional
    public void registroMascota(String nombre, String raza, String sexo) throws ErrorServicio {
//        validar(nombre, raza,fechaNacimiento);
//    Date fecha=new Date();

        Cliente cliente = clienteServicio.getCliente();

        Mascota mascota;
        mascota = new Mascota();
        mascota.setNombre(nombre);
        mascota.setRaza(raza);
        mascota.setSexo(sexo);

        System.out.println(mascota.toString());

        if (false) {
            throw new ErrorServicio("Comletar todos los campos");
        }
        mascotaRepositorio.save(mascota);
        System.out.println("Guardó la mascota");

        List<Mascota> listaMascota = new ArrayList();
        try {
            listaMascota = cliente.getMascota();

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("+++++++++++++++++++++++++++++" + "error en la lista mascota");
        }

        System.out.println("Trajo la lista");
        listaMascota.add(mascota);
        System.out.println("guardó la lista");
        cliente.setMascota(listaMascota);
        System.out.println("guardó el cliente");
        clienteRepositorio.save(cliente);

    }

    public void validation(String nombre, String raza, String sexo) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("Completar nombre");
        }

        if (raza == null || raza.isEmpty()) {
            throw new ErrorServicio("Completar raza");

        }
        if (sexo == null || sexo.isEmpty()) {
            throw new ErrorServicio("Completar el sexo");
        } else {
        }

    }

//       @Transactional
//    public void modificaciónMascota(String nombre, String ID, String raza, String sexo) throws ErrorServicio{
//        validation(nombre, raza, sexo);
//        Optional<Mascota> respuesta = MascotaRepositorio.findById(ID);
//        if(respuesta != null){
//            Mascota mascota = respuesta;
//        mascota.setNombre(nombre);
//        mascota.setRaza(raza);
//        mascota.setSexo(sexo);
//        mascotaRepositorio.save(mascota);
//        }else{
//            throw new ErrorServicio("La mascota no existe");
//        }
//    }
//     public Mascota getMascota(){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Mascota m = mascotaRepositorio.findByName(auth.getName());
//        return m;
//    }
//    public Date convertirDate(String fecha) {
//        
//        try {
//            DateFormat fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd");
//            Date convertido = fechaNacimiento.parse(fecha);
//            
//            return convertido;
//        } catch (ParseException ex) {
//            Logger.getLogger(MascotaServicio.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
    
    public List<Mascota> listarMascotasPorCliente(String documento){
        List<Mascota> listadoMascotas = new ArrayList();
        return listadoMascotas = mascotaRepositorio.listarMascotasPorCliente(documento);
     }
    
    public List<Consulta> historia_clinica(String Id) throws ErrorServicio {
        Optional<Mascota> mascota = mascotaRepositorio.findById(Id);

        if (mascota.isPresent()) {
            List<Consulta> consultas = mascota.get().getHistoriaClinica();
            return consultas;
        } else {
            throw new ErrorServicio("No se encontro la lista de consultas");
        }
    }
    
    public void eliminarMascota(String id) throws ErrorServicio{
        Optional<Mascota> mascota = mascotaRepositorio.findById(id);
        if(mascota.isPresent()){
            mascotaRepositorio.delete(mascota.get());
        }else{
            throw new ErrorServicio("No se encontro la mascota");
        }
        
    }
    
    public Mascota buscarMascota(String id) throws ErrorServicio{
        Mascota mascota = mascotaRepositorio.buscarPorId(id);
        if(mascota != null){
            return mascota;
        }else{
            throw new ErrorServicio("No se encontro la mascota");
        }
    }
}
