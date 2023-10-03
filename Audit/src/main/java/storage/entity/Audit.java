package storage.entity;

import enams.EntityType;
import lombok.*;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "audit_fitness")
public class Audit {
    @Id
    private UUID id;
    @Column(name = "dt_create")
    private Long dtCreate;
    @Column(name = "dt_update")
    private Long dtUpdate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String text;
    @Enumerated(value = EnumType.STRING)
    private EntityType type;
    private String uid;

}
