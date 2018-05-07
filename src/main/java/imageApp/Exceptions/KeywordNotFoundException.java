
package imageApp.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No image with such id was found.")
public class KeywordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public KeywordNotFoundException(String key){
        super(key);
    }
}