package by.it_akademy.fitness.odto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OutputResponseError {
    private String logref;
    private String message;
}

