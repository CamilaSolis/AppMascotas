
package proyecto.egg.AppMascota.Repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.egg.AppMascota.Entidades.Consulta;

@Repository
public interface ConsultaRepositorio extends JpaRepository<Consulta, String>{
    
 @Query("SELECT c FROM Consulta c INNER JOIN Mascota m ON c.mascota_id = m.id WHERE c.mascota_id = :idMascota AND m.id = :idMascota")
public List<Consulta> buscarConsultaPorMascota(@Param("idMascota") String idMascota);
}
