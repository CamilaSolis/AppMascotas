/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.egg.AppMascota.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.egg.AppMascota.Entidades.Mascota;

@Repository
public interface MascotaRepositorio extends JpaRepository<Mascota,String> {
    
}
