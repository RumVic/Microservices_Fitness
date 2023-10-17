package by.it_akademy.fitness.idto;

import lombok.*;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Component
public class InputUserDTO {
    @NotBlank
    @Pattern(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$",
            message = "Email invalid")
    private String mail;
    @NotBlank
    private String nick;
    private String password;
}
