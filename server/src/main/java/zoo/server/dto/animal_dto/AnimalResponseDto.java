
package zoo.server.dto.animal_dto;

import java.time.LocalDate;

public record AnimalResponseDto(
        
        Long id,
        String nome,
        String descricao,
        LocalDate data_nascimento,
        String especie,
        String habitat,
        String pais_origem
        
        ) {}
