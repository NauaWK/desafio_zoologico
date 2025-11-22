
package zoo.server.utils.mappers;

import org.springframework.stereotype.Component;
import zoo.server.dto.cuidado_dto.CuidadoRequestDto;
import zoo.server.dto.cuidado_dto.CuidadoResponseDto;
import zoo.server.entity.Animal;
import zoo.server.entity.Cuidado;

@Component
public class CuidadoMappers {
    
    public Cuidado toCuidado(CuidadoRequestDto dto, Animal animal){
        return new Cuidado(
                dto.nomeCuidado(),
                dto.descricao(),
                dto.frequencia(),
                animal);            
    }
       
    public CuidadoResponseDto toDto(Cuidado cuidado){
        return new CuidadoResponseDto(
                cuidado.getId(),
                cuidado.getNomeCuidado(),
                cuidado.getDescricao(),
                cuidado.getFrequencia(),
                cuidado.getAnimal().getId());            
    }

}
