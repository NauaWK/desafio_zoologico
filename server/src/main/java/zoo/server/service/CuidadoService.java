
package zoo.server.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import zoo.server.dto.cuidado_dto.CuidadoRequestDto;
import zoo.server.dto.cuidado_dto.CuidadoResponseDto;
import zoo.server.entity.Animal;
import zoo.server.entity.Cuidado;
import zoo.server.exception.custom_exceptions.ObjectAlreadyExistsException;
import zoo.server.exception.custom_exceptions.ObjectNotFoundException;
import zoo.server.repository.AnimalRepository;
import zoo.server.repository.CuidadoRepository;
import zoo.server.utils.mappers.CuidadoMappers;

@Service
public class CuidadoService {
    
    private final CuidadoMappers mappers;
    private final CuidadoRepository repository;
    private final AnimalRepository animalRepository;

    public CuidadoService(CuidadoMappers mappers, CuidadoRepository repository, AnimalRepository animalRepository) {
        this.mappers = mappers;
        this.repository = repository;
        this.animalRepository = animalRepository;
    }
    
    public List<CuidadoResponseDto> getAllCuidados(){
        List<Cuidado> cuidados = repository.findAll();
        
        List<CuidadoResponseDto> response = cuidados.stream().map(cuidado -> new CuidadoResponseDto(           
                        cuidado.getId(),
                        cuidado.getNomeCuidado(),
                        cuidado.getDescricao(),
                        cuidado.getFrequencia(),
                        cuidado.getAnimal().getId())).collect(Collectors.toList());
               
        return response;  
    }
    
    public CuidadoResponseDto getCuidadoById(Long id){
        Cuidado cuidado = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cuidado com id " + id + " não encontrado!"));
        
        return mappers.toDto(cuidado);
    }
    
    public Cuidado addCuidado(CuidadoRequestDto dto){
        if(repository.existsByNomeCuidado(dto.nomeCuidado())){
            throw new ObjectAlreadyExistsException("O cuidado " + dto.nomeCuidado() + " já existe!");
        }
        Animal animal = animalRepository.findById(dto.animal_id())
                .orElseThrow(() -> new ObjectNotFoundException("Animal com id " + dto.animal_id() + " não encontrado!"));
        
        return repository.save(mappers.toCuidado(dto, animal));
    }
    
    public CuidadoResponseDto updateCuidado(CuidadoRequestDto dto, Long id){
        Cuidado cuidado = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cuidado com id " + id + " não encontrado!"));       
        Animal animal = animalRepository.findById(dto.animal_id())
                .orElseThrow(() -> new ObjectNotFoundException("Animal com id " + dto.animal_id() + " não encontrado!"));       
        
        cuidado.setNomeCuidado(dto.nomeCuidado());
        cuidado.setDescricao(dto.descricao());
        cuidado.setFrequencia(dto.frequencia());
        cuidado.setAnimal(animal);
        repository.save(cuidado);
        return mappers.toDto(cuidado);    
    }
    
    public void deleteCuidado(Long id){
        repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cuidado com id " + id + " não encontrado!"));  
        
        repository.deleteById(id);       
    }
     
}
