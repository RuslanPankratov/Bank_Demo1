package bank.demo.dto.core.validation;

public class ValidationException extends RuntimeException {

    public ValidationException(String message){
        super(message);
    }
}
