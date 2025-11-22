
package zoo.server.utils.mappers;

import org.springframework.stereotype.Component;
import zoo.server.dto.animal_dto.AnimalRequestDto;
import zoo.server.dto.animal_dto.AnimalResponseDto;
import zoo.server.entity.Animal;

@Component
public class AnimalMappers {
    
    public Animal toAnimal(AnimalRequestDto dto){
        return new Animal(
                dto.nome(),
                dto.descricao(),
                dto.data_nascimento(),
                dto.especie(),
                dto.habitat(),
                dto.pais_origem());
    }  
    
    public AnimalResponseDto toDto(Animal animal){
        return new AnimalResponseDto(
                animal.getId(),
                animal.getNome(),
                animal.getDescricao(),
                animal.getData_nascimento(),
                animal.getEspecie(),
                animal.getHabitat(),
                animal.getPais_origem());
    }
      
}
