
package zoo.server.dto.cuidado_dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import zoo.server.utils.enums.FrequenciaCuidado;

public record CuidadoRequestDto(
        
        @NotBlank(message = "Nome do cuidado é obrigatório!")
        @Size(min = 10, max = 30, message = "Nome do cuidado precisa ter entre 10 a 30 carácteres")
        String nomeCuidado,
        
        //descrição opcional
        @Size(max = 50, message = "Descrição do cuidado não pode ultrapassar 50 carácteres")
        String descricao,
        
        @NotNull(message = "Frequência do cuidado é obrigatória!")
        FrequenciaCuidado frequencia,
        
        @NotNull(message = "Identificador do animal relacionado a este cuidado é obrigatório!")
        Long animal_id
        
        ) {}
