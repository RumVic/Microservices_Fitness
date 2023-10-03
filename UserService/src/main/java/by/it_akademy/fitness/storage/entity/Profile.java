package by.it_akademy.fitness.storage.entity;

import by.it_akademy.fitness.enams.EGender;
import by.it_akademy.fitness.enams.ELifestyle;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "profile_fitness")
public class Profile {
    @Id
    private UUID id;
    @Column(name = "dt_create")
    private Long dtCreate;
    @Column(name = "dt_update")
    private Long dtUpdate;
    @OneToOne()
    private User user;
    private double height;
    private double weight;
    private Date birthday;
    @Enumerated(value = EnumType.STRING)
    private EGender gender;
    @Enumerated(value = EnumType.STRING)
    private ELifestyle lifestyle;
    private double targetWeight;

}
