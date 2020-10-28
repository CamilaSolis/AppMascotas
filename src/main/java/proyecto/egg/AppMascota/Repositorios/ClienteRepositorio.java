
package proyecto.egg.AppMascota.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.egg.AppMascota.Entidades.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente,String> {
    
     @Query("SELECT c FROM Cliente c WHERE c.email=:email")
    public Cliente buscarPorMail(@Param("email") String email);
    
     @Query("SELECT c FROM Cliente c WHERE c.documento=:documento")
    public Cliente buscarPorDocumento(@Param("documento") String documento);
}
