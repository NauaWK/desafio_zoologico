
package zoo.server.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zoo.server.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    
    //método personalizado para verificação de animais já existentes no banco (pelo nome)
    public boolean existsByNome(String none);
    
    //consulta JPQL personalizada com base nos filtros opcionais
    @Query("""
        SELECT a FROM Animal a
        WHERE (:habitat IS NULL OR a.habitat = :habitat)
        AND (:especie IS NULL OR a.especie = :especie)
    """)
    public List<Animal> getAnimalsWithFilters(String habitat, String especie);
    
    
}
