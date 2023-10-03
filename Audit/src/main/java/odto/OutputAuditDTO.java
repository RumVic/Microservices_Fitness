package odto;

import enams.EntityType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OutputAuditDTO {
    private OutputUserDTO user;
    private String text;
    private EntityType type;
    private String uid;

}
