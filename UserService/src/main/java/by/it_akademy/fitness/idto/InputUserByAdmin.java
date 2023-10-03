package by.it_akademy.fitness.idto;

import by.it_akademy.fitness.enams.EStatus;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class InputUserByAdmin {

    @NotBlank
    @Pattern(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$",
            message = "Email invalid") private String mail;
    @NotNull
    private String nick;
    @NotNull
    private String password;
    @NotNull
    private String role;
    private EStatus status;

}
