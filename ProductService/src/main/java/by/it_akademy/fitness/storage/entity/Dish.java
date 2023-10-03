package by.it_akademy.fitness.storage.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dish_fitness")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Dish {

    @Id
    private UUID id;
    @Column(name = "dt_create")
    private Long dtCreate;
    @Column(name = "dt_update")
    private Long dtUpdate;
    private String title;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dish")
    private  List<CompositionDish> compositionDishes ;
    @Column(name = "create_by_role")
    private String createByRole;
    public List<CompositionDish> getCompositionDishList() {
        return compositionDishes;
    }

}
