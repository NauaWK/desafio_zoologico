
package zoo.server.dto.animal_dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;


public record AnimalRequestDto(
        
        @NotBlank(message = "Nome do animal é obrigatório!")
        @Size(min = 3, max = 20, message = "Nome do animal precisa ter entre 3 a 20 carácteres")
        String nome,
        
        //descrição opcional
        @Size(max = 50, message = "Descrição do animal não pode ultrapassar 50 carácteres")
        String descricao,
        
        @NotNull(message = "Data de nascimento do animal é obrigatória!")
        @Past(message = "A data de nascimento do animal não pode estar no futuro!")
        LocalDate data_nascimento,
        
        @NotBlank(message = "Espécie do animal é obrigatória!")
        @Size(min = 3, max = 40, message = "Espécie do animal precisa ter entre 5 a 40 carácteres")
        String especie,
        
        @NotBlank(message = "Habitat do animal é obrigatório!")
        @Size(min = 3, max = 40, message = "Habitat do animal precisa ter entre 4 a 40 carácteres")
        String habitat,
        
        @NotBlank(message = "País de origem do animal é obrigatório!")
        @Size(min = 3, max = 40, message = "País de origem do animal precisa ter entre 4 a 40 carácteres")
        String pais_origem
        
        ) {}
