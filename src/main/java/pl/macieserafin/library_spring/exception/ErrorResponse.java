package pl.macieserafin.library_spring.exception;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
}
