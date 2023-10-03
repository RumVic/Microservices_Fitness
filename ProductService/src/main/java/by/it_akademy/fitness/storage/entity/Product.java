package by.it_akademy.fitness.storage.entity;


import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "product_fitness")
public class Product {
    @Id
    private UUID id;
    @Column(name = "dt_create")
    private Long dtCreate;
    @Column(name = "dt_update")
    private Long dtUpdate;

    private String title;

    private double weight;

    private double calories;

    private double proteins;

    private double fats;

    private double carbohydrates;
    @Column(name = "created_by_role")
    private String createdByRole; // who created product in db;
}



