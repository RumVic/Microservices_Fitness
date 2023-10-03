package by.it_akademy.fitness.storage.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name ="diary_food_fitness")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DiaryFood {
    @Id
    private UUID id;
    @Column(name = "dt_create")
    private Long dtCreate;
    @Column(name = "dt_update")
    private Long dtUpdate;
    @Column(name = "dt_supply")
    private Long dtSupply;

    @ManyToOne(targetEntity = Dish.class )
    @JoinColumn
    private Dish dish;

    @Column(name = "weight_dish")
    private double weightDish;

    @ManyToOne(targetEntity = Product.class)
    private Product product;

    @Column(name = "weight_product")
    private double weightProduct;

    private UUID profile;
}
