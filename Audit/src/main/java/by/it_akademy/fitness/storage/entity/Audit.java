package by.it_akademy.fitness.storage.entity;

import by.it_akademy.fitness.enams.EntityType;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "audit_fitness",schema = "appaudit")
@Entity
public class Audit {
    @Id
    private UUID id;

    @Column(name = "dt_create")
    private Long dtCreate;

    @Column(name = "dt_update")
    private Long dtUpdate;

    //@ManyToOne
    //@JoinColumn(name = "user_id")
    //private User user_id;

    private String text;

    @Enumerated(value = EnumType.STRING)
    private EntityType type;

    private String uid;
}
