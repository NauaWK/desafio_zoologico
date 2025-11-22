
package zoo.server.controller;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zoo.server.dto.animal_dto.AnimalRequestDto;
import zoo.server.dto.animal_dto.AnimalResponseDto;
import zoo.server.entity.Animal;
import zoo.server.service.AnimalService;

@RestController
@RequestMapping("/api/animais")
public class AnimalController {
    
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }
    
    @GetMapping
    public ResponseEntity<List<AnimalResponseDto>> getAllAnimals(@RequestParam(required = false) String habitat,
                                                                 @RequestParam(required = false) String especie){ 
        
        if(habitat != null || especie != null){
            return ResponseEntity.ok().body(animalService.getAnimalsWithFilters(habitat, especie));
        }
        return ResponseEntity.ok().body(animalService.getAllAnimals());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponseDto> getAnimalById(@PathVariable Long id){
        return ResponseEntity.ok().body(animalService.getAnimalById(id));
    }  
    
    @PostMapping
    public ResponseEntity<Void> addAnimal(@Valid @RequestBody AnimalRequestDto dto){
        Animal animal = animalService.addAnimal(dto);
        return ResponseEntity.created(URI.create("/api/animal/" + animal.getId())).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponseDto> updateAnimal(@Valid @RequestBody AnimalRequestDto dto, @PathVariable Long id){
        return ResponseEntity.ok().body(animalService.updateAnimal(dto, id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id){
        animalService.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }      
          
}
