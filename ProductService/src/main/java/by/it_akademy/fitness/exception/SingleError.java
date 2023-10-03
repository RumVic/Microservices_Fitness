package by.it_akademy.fitness.exception;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SingleError {

    private String field;
    private String message;

}
