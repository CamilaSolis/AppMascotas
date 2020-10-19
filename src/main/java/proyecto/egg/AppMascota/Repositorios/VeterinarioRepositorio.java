
package proyecto.egg.AppMascota.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import org.springframework.stereotype.Repository;
import proyecto.egg.AppMascota.Entidades.Veterinario;


@Repository
public interface VeterinarioRepositorio extends JpaRepository <Veterinario, String>{
    
    @Query("SELECT v FROM Veterinario v WHERE v.matricula=:matricula")
    public Veterinario buscarVeterinarioPorMatricula(@Param("matricula")String matricula);
    
     

}

