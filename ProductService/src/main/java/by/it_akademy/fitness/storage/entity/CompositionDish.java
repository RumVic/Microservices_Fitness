package by.it_akademy.fitness.storage.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name ="composition_dish_fitness")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CompositionDish {
    @Id
    private UUID id;
    @Column(name = "dt_create")
    private Long dtCreate;
    @Column(name = "dt_update")
    private Long dtUpdate;

    private String title;

    private UUID dish; // reference to dish

    @OneToOne(fetch = FetchType.EAGER)//cascade = CascadeType.ALL,
    //@JoinColumn(insertable = false, updatable = false)
    private Product product;

    private double weight;

}
