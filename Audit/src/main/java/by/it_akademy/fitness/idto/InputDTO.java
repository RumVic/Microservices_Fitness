package by.it_akademy.fitness.idto;

import by.it_akademy.fitness.enams.EntityType;
import lombok.*;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class InputDTO {
    @NotNull
    private EntityType entityType;
    @NotNull
    private String description;
    @NotNull
    private String userId;
    @NotNull
    private String entityId;
}

