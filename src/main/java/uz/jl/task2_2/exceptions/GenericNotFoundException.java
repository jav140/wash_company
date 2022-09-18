package uz.jl.task2_2.exceptions;

/**
 * @author "Isroilov Javohir"
 * @since 19/08/22/15:57 (Friday)
 * spring-boot-features/IntelliJ IDEA
 */
public class GenericNotFoundException extends GenericRuntimeException {
    public GenericNotFoundException(String message, Integer statusCode) {
        super(message, statusCode);
    }
}
