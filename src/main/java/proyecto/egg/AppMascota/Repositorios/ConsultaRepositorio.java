
package proyecto.egg.AppMascota.Repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.egg.AppMascota.Entidades.Consulta;

@Repository
public interface ConsultaRepositorio extends JpaRepository<Consulta, String>{
    
    @Query(value="SELECT c.* FROM consulta c INNER JOIN mascota_historia_clinica m ON c.id = m.historia_clinica_id WHERE m.mascota_id = :id", nativeQuery = true)
    public List<Consulta> listarConsultasPorMascota2(@Param("id") String id);
 


}
