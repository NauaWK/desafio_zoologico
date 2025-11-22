
package zoo.server.exception.custom_exceptions;


public class ObjectAlreadyExistsException extends RuntimeException {

    public ObjectAlreadyExistsException(String msg) {
        super(msg);
    }
    
}
