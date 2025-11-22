
package zoo.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zoo.server.entity.Cuidado;

@Repository
public interface CuidadoRepository extends JpaRepository<Cuidado, Long> {
    
    public boolean existsByNomeCuidado(String nomeCuidado);
    
    
}
