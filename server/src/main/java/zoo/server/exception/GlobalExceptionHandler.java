
package zoo.server.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zoo.server.exception.custom_exceptions.InvalidEnumException;
import zoo.server.exception.custom_exceptions.ObjectAlreadyExistsException;
import zoo.server.exception.custom_exceptions.ObjectNotFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {
    
    
    //handler para exceções lançadas nas validações dos Dtos de qualquer entidade
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex){
        
        //HashMap conténdo pares: campo com erro, mensagem do erro
        Map<String, Object> errors = new HashMap<>();        
        errors.put("status", "400");
        errors.put("timestamp", LocalDateTime.now().withNano(0));
        errors.put("error", "1 ou mais campos inválidos");
        
        //selecionando todos os campos com erros da exceção e inserindo no HashMap "errors"
        ex.getBindingResult().getFieldErrors().forEach(error -> {
        errors.put(error.getField(), error.getDefaultMessage());            
        });             
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
       
    @ExceptionHandler(InvalidEnumException.class)
    public ResponseEntity<ErrorResponse> invalidEnumExceptionHandler(InvalidEnumException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("400", ex.getMessage()));       
    }  
    
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponse> objectNotFoundExceptionhandler(ObjectNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("404", ex.getMessage()));       
    } 
    
    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> ObjectAlreadyExistsExceptionHandler(ObjectAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("400", ex.getMessage()));       
    } 
    
}
