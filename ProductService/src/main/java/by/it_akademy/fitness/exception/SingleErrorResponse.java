package by.it_akademy.fitness.exception;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SingleErrorResponse {

    private String logref;
    private String message;
}
