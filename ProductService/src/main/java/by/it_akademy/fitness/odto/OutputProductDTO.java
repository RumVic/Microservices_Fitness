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

//    public OutputProductDTO(UUID id,
//                            Long dtCreate,
//                            Long dtUpdate,
//                            String title,
//                            double weight,
//                            double calories,
//                            double proteins,
//                            double fats,
//                            double carbohydrates) {
//        this.id = id;
//        this.dtCreate = dtCreate;
//        this.dtUpdate = dtUpdate;
//        this.title = title;
//        this.weight = weight;
//        this.calories = calories;
//        this.proteins = proteins;
//        this.fats = fats;
//        this.carbohydrates = carbohydrates;
//    }
//
//    public OutputProductDTO() {
//
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    public void setDtCreate(Long dtCreate) {
//        this.dtCreate = dtCreate;
//    }
//
//    public void setDtUpdate(Long dtUpdate) {
//        this.dtUpdate = dtUpdate;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public void setWeight(double weight) {
//        this.weight = weight;
//    }
//
//    public void setCalories(double calories) {
//        this.calories = calories;
//    }
//
//    public void setProteins(double proteins) {
//        this.proteins = proteins;
//    }
//
//    public void setFats(double fats) {
//        this.fats = fats;
//    }
//
//    public void setCarbohydrates(double carbohydrates) {
//        this.carbohydrates = carbohydrates;
//    }
//
//    public UUID getId() {
//        return id;
//    }
//
//    public Long getDtCreate() {
//        return dtCreate;
//    }
//
//    public Long getDtUpdate() {
//        return dtUpdate;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public double getWeight() {
//        return weight;
//    }
//
//    public double getCalories() {
//        return calories;
//    }
//
//    public double getProteins() {
//        return proteins;
//    }
//
//    public double getFats() {
//        return fats;
//    }
//
//    public double getCarbohydrates() {
//        return carbohydrates;
//    }
}
