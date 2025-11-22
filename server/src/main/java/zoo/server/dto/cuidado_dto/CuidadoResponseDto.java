
package zoo.server.dto.cuidado_dto;

import zoo.server.utils.enums.FrequenciaCuidado;

public record CuidadoResponseDto( 
  
        Long id,
        String nomeCuidado,
        String descricao,
        FrequenciaCuidado frequencia,
        Long animal_id
        
        ) {}
