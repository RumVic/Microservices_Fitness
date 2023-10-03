package by.it_akademy.fitness.odto;

import lombok.*;

import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OutputDishDTO {

    private UUID id;
    private Long dtCreate;
    private Long dtUpdate;
    private String title;
    private List<OutputComDishDTO> compositionDishes;
    private String createByRole;
}