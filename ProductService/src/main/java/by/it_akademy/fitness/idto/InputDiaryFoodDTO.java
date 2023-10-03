package by.it_akademy.fitness.idto;

import by.it_akademy.fitness.storage.entity.Dish;
import by.it_akademy.fitness.storage.entity.Product;
import lombok.*;

import javax.validation.constraints.Min;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class InputDiaryFoodDTO {
    private Long dtSupply;
    private Dish dish;
    @Min(value = 0)
    private double weightDish;
    private Product product;
    @Min(value = 0)
    private double weightProduct;
    private UUID profile;
}

