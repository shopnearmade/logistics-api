package NearMade.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiError> handleOrderNotFoundException(OrderNotFoundException exception) {
        ApiError error = new ApiError (404,exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleNotValidArgument(MethodArgumentNotValidException exception)  {
        // need to review before push my changes and understand this line
        String errorMessage = exception.getBindingResult().getFieldError().getDefaultMessage();
        ApiError error = new ApiError(400,errorMessage);

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }




}
