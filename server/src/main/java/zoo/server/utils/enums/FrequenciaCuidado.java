
package zoo.server.utils.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import zoo.server.exception.custom_exceptions.InvalidEnumException;

public enum FrequenciaCuidado {
    
    DIARIA, SEMANAL, MENSAL, ANUAL;
    
    //tentativa de conversão de string do JSON para enum, permitindo maior flexbilidade na sintaxe
    @JsonCreator
    public static FrequenciaCuidado from(String frequencia){
        try {
            return FrequenciaCuidado.valueOf(frequencia.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumException("Frequência inválida: " + frequencia);
        }                       
    }   
    
}
