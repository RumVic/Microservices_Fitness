package by.it_akademy.fitness.odto;




import by.it_akademy.fitness.enams.EGender;
import by.it_akademy.fitness.enams.ELifestyle;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OutputProfileDTO {

    private UUID id;
    private Long dtCreate;
    private Long dtUpdate;
    private double height;
    private double weight;
    private Date birthday;
    private EGender gender;
    private ELifestyle lifestyle;
    private double targetWeight;
    private OutputUserDTO user;
}
