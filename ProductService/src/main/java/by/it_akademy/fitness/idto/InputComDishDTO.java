package by.it_akademy.fitness.idto;


import by.it_akademy.fitness.storage.entity.Product;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class InputComDishDTO {
    private UUID id;
    private Long dtCreate;
    private Long dtUpdate;
    private String title;
    private UUID dish; // reference to dish
    private Product product;
    private double weight;

}
