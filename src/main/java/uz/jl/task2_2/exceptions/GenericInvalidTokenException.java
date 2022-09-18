package uz.jl.task2_2.exceptions;

import lombok.Getter;

/**
 * @author "Isroilov Javohir"
 * @since 19/08/22/15:21 (Friday)
 * spring-boot-features/IntelliJ IDEA
 */

@Getter
public class GenericInvalidTokenException extends GenericNotFoundException {
    public GenericInvalidTokenException(String message, Integer statusCode) {
        super(message, statusCode);
    }
}
