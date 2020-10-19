
package proyecto.egg.AppMascota.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.egg.AppMascota.Entidades.Veterinario;


@Repository
public interface VeterinarioRepositorio extends JpaRepository<Veterinario, String>{
    
    
}
