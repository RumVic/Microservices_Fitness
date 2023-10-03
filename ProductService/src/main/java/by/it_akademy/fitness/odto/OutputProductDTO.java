package by.it_akademy.fitness.odto;

import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OutputProductDTO {

    private UUID id;

    private Long dtCreate;

    private Long dtUpdate;

    private String title;

    private double weight;

    private double calories;

    private double proteins;

    private double fats;

    private double carbohydrates;
}
