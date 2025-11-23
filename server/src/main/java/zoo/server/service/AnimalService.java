
package zoo.server.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import zoo.server.dto.animal_dto.AnimalRequestDto;
import zoo.server.dto.animal_dto.AnimalResponseDto;
import zoo.server.entity.Animal;
import zoo.server.exception.custom_exceptions.ObjectAlreadyExistsException;
import zoo.server.exception.custom_exceptions.ObjectNotFoundException;
import zoo.server.repository.AnimalRepository;
import zoo.server.utils.mappers.AnimalMappers;

@Service
public class AnimalService {
    
    private final AnimalMappers mappers;
    private final AnimalRepository repository;

    public AnimalService(AnimalMappers mappers, AnimalRepository repository) {
        this.mappers = mappers;
        this.repository = repository;
    }
    
    public List<AnimalResponseDto> getAllAnimals(){
        List<Animal> animals = repository.findAll();     
              
        //convertendo cada Animal em AnimalResponseDto
        List<AnimalResponseDto> response = animals.stream().map(animal -> new AnimalResponseDto(
                animal.getId(),
                animal.getNome(),
                animal.getDescricao(),
                animal.getData_nascimento(),
                animal.getEspecie(),
                animal.getHabitat(),
                animal.getPais_origem())).collect(Collectors.toList());  
                
                return response;                
    }
    
    public AnimalResponseDto getAnimalById(Long id){
        Animal animal =  repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Animal com id " + id + " não encontrado!"));
        
        return mappers.toDto(animal);
    }
    
    public List<AnimalResponseDto> getAnimalsWithFilters(String habitat, String especie){
        List<Animal> animals =  repository.getAnimalsWithFilters(habitat, especie);
        
        if(animals.isEmpty()){
            throw new ObjectNotFoundException("Nenhum animal encontrado!");
        }
        
        List<AnimalResponseDto> response = animals.stream().map(animal -> new AnimalResponseDto(
                animal.getId(),
                animal.getNome(),
                animal.getDescricao(),
                animal.getData_nascimento(),
                animal.getEspecie(),
                animal.getHabitat(),
                animal.getPais_origem())).collect(Collectors.toList());  
                
                return response; 
    }
    

    public Animal addAnimal(AnimalRequestDto dto) {
        if(repository.existsByNome(dto.nome())){
            throw new ObjectAlreadyExistsException("O animal " + dto.nome() + " já existe!");
        }
        return repository.save(mappers.toAnimal(dto));
    }
     
    public AnimalResponseDto updateAnimal(AnimalRequestDto dto, Long id){
        Animal animal = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Animal com id " + id + " não encontrado!"));
        
        animal.setNome(dto.nome());
        animal.setDescricao(dto.descricao());
        animal.setData_nascimento(dto.data_nascimento());
        animal.setEspecie(dto.especie());
        animal.setHabitat(dto.habitat());
        animal.setPais_origem(dto.pais_origem());
        
        repository.save(animal);
        return mappers.toDto(animal);
    }
    
    public void deleteAnimal(Long id){      
        repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Animal com id " + id + " não encontrado!"));
         
        repository.deleteById(id);      
    }
    
    
}


