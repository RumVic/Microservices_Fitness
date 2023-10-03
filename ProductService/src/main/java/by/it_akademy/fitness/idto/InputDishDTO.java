package by.it_akademy.fitness.idto;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class InputDishDTO {
    @NotNull //javax validation constraint
    private String title;

    @NotEmpty
    private List<InputComDishDTO> comDishDTO;
}
