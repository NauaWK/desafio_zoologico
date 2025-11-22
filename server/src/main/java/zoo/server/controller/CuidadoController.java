
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
import org.springframework.web.bind.annotation.RestController;
import zoo.server.dto.cuidado_dto.CuidadoRequestDto;
import zoo.server.dto.cuidado_dto.CuidadoResponseDto;
import zoo.server.entity.Cuidado;
import zoo.server.service.CuidadoService;

@RestController
@RequestMapping("/api/cuidado")
public class CuidadoController {
    
    private final CuidadoService cuidadoService;

    public CuidadoController(CuidadoService cuidadoService) {
        this.cuidadoService = cuidadoService;
    }
          
    @GetMapping
    public ResponseEntity<List<CuidadoResponseDto>> getAllCuidados(){
        return ResponseEntity.ok().body(cuidadoService.getAllCuidados());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CuidadoResponseDto> getCuidadoById(@PathVariable Long id){
        return ResponseEntity.ok().body(cuidadoService.getCuidadoById(id));
    }
    
    @PostMapping
    public ResponseEntity<Void> addCuidado(@Valid @RequestBody CuidadoRequestDto dto){
        Cuidado cuidado = cuidadoService.addCuidado(dto);
        return ResponseEntity.created(URI.create("/api/cuidado/" + cuidado.getId())).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CuidadoResponseDto> updateCuidado(@Valid @RequestBody CuidadoRequestDto dto, @PathVariable Long id){
        return ResponseEntity.ok().body(cuidadoService.updateCuidado(dto, id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuidado(@PathVariable Long id){
        cuidadoService.deleteCuidado(id);
        return ResponseEntity.noContent().build();
    }  
    
}
