
package proyecto.egg.AppMascota.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.egg.AppMascota.Entidades.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente,String> {
    
     @Query("SELECT c FROM Cliente c WHERE c.mail = :mail")
    public Cliente buscarPorMail(@Param("mail") String mail);
    
}
