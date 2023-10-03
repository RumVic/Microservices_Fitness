package by.it_akademy.fitness.idto;



import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class InputProductDTO {
    @NotNull
    private String title;
    @Min(value = 0, message ="No can have this value" )
    private double weight;
    @Min(value = 0, message ="No can have this value" )
    private double calories;
    @Min(value = 0, message ="No can have this value" )
    private double proteins;
    @Min(value = 0, message ="No can have this value" )
    private double fats;
    @Min(value = 0, message ="No can have this value" )
    private double carbohydrates;
}
