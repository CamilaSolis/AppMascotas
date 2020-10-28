/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.egg.AppMascota.Repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.egg.AppMascota.Entidades.Cliente;
import proyecto.egg.AppMascota.Entidades.Mascota;

@Repository
public interface MascotaRepositorio extends JpaRepository<Mascota,String> {

     @Query("SELECT m FROM Mascota m WHERE m.id=:id")
    public Mascota buscarPorId(@Param("id") String id);
    
     @Query("SELECT m FROM Cliente c,IN(c.mascota) m WHERE c.documento=:documento")
    public List<Mascota> listarMascotasPorCliente(@Param("documento") String documento);
    
    
}
