package uz.jl.task2_2.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDTO {

    @NotBlank(message = "contact cannot be null")
    private String contact;

    @NotBlank(message = "message cannot be null")
    private String message;

    private String toEmail;

}
