package by.it_akademy.fitness.idto;

import lombok.*;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class InputDTO implements IDto {
    @NotNull
    private String entityType;
    @NotNull
    private String description;
    @NotNull
    private String userId;
    @NotNull
    private String entityId;

    @Override
    public InputDTO getter() {
        return this;
    }
}

