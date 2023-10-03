package by.it_akademy.fitness.odto;

import by.it_akademy.fitness.enams.EStatus;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OutputUserDTO {

    private UUID id;
    private Long dtCrate;
    private Long dtUpdate;
    private String login;
    private String role;
    private EStatus status;

}
