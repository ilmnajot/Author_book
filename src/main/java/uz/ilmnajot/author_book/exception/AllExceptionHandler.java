package uz.ilmnajot.author_book.exception;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AllExceptionHandler {


    @ExceptionHandler(ElementNotFoundException.class)
    public HttpEntity<?> handleElementNotFoundException(
            ElementNotFoundException elementNotFoundException,
            WebRequest webRequest
    ) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                elementNotFoundException.getMessage(),
                webRequest.getDescription(false));

                return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
