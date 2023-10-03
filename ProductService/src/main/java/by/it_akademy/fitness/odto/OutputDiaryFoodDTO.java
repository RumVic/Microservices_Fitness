package by.it_akademy.fitness.odto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OutputDiaryFoodDTO {

    private UUID id;
    private Long dtCreate;
    private Long dtUpdate;
    private Long dtSupply;
    private OutputDishDTO dish;
    private double weightDish;
    private OutputProductDTO product;
    private double weightProduct;
    private UUID profile;
}