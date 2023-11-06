package by.it_akademy.fitness.storage.entity;

import by.it_akademy.fitness.enams.EStatus;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
@Table(name = "user_fitness", schema = "appuser")
// It is truly  needed. without definition schema hibernate cont mate query right way
public class User {//implements UserDetails
    @Id
    private UUID id;
    @Column(name = "dt_create")
    private Long dtCrate;
    @Column(name = "dt_update")
    private Long dtUpdate;
    private String username;
    @Column(unique = true)
    private String login;
    private String activationCode;
    private String password;
    private String role;
    @Enumerated(value = EnumType.STRING)
    private EStatus status;
}
