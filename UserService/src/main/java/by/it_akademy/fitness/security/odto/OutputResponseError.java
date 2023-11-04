package by.it_akademy.fitness.security.odto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutputResponseError {
    private String logref;
    private String message;
}

