package by.it_akademy.fitness.idto;

import by.it_akademy.fitness.enams.EGender;
import by.it_akademy.fitness.enams.ELifestyle;
import by.it_akademy.fitness.serializator.DateDeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class InputProfileDTO {
    private double height;
    private double weight;
    @JsonDeserialize(using = DateDeSerializer.class)
    private Date dt_birthday;
    private double target;
    private ELifestyle activity_type;
    private EGender sex;


}
