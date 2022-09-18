package uz.jl.task2_2.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.jl.task2_2.exceptions.GenericNotFoundException;
import uz.jl.task2_2.exceptions.GenericRuntimeException;
import uz.jl.task2_2.response.ApiErrorResponse;
import uz.jl.task2_2.response.ApiResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;



@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(GenericNotFoundException.class)
    public ApiResponse<ApiErrorResponse> handle404(GenericNotFoundException e, HttpServletRequest request) {
        return new ApiResponse<>(ApiErrorResponse.builder()
                .friendlyMessage(e.getMessage())
                .developerMessage(Arrays.toString(e.getStackTrace()))
                .requestPath(request.getRequestURL().toString())
                .build(),
                e.getStatusCode());
    }
    @ExceptionHandler(GenericRuntimeException.class)
    public ApiResponse<ApiErrorResponse> badGetAway(GenericRuntimeException e, HttpServletRequest request){
        return new ApiResponse<>(ApiErrorResponse.builder()
                .friendlyMessage(e.getMessage())
                .developerMessage(Arrays.toString(e.getStackTrace()))
                .requestPath(request.getRequestURL().toString())
                .build(),
                e.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, Object> errors = new HashMap<>();
        BindingResult bindingResult = ex.getBindingResult();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        String requestURI = request.getRequestURI();
        return new ResponseEntity<>(ApiErrorResponse
                .builder()
                .friendlyMessage("Invalid Params Provided")
                .errorFields(errors)
                .requestPath(requestURI)
                .build(), HttpStatus.BAD_REQUEST);
    }
}
