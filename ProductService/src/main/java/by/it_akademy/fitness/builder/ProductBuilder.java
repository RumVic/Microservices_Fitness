package by.it_akademy.fitness.builder;

import by.it_akademy.fitness.storage.entity.Product;

import java.util.UUID;

public class ProductBuilder {

    private UUID id;
    
    private Long dtCreate;
    
    private Long dtUpdate;

    private String title;

    private double weight;

    private double calories;

    private double proteins;

    private double fats;

    private double carbohydrates;
    
    private String createdByRole;

    private ProductBuilder() {
    }
    
    public static ProductBuilder create(){
        return new ProductBuilder();
    }

    public ProductBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public ProductBuilder setDtCreate(Long dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public ProductBuilder setDtUpdate(Long dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public ProductBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ProductBuilder setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    public ProductBuilder setCalories(double calories) {
        this.calories = calories;
        return this;
    }

    public ProductBuilder setProteins(double proteins) {
        this.proteins = proteins;
        return this;
    }

    public ProductBuilder setFats(double fats) {
        this.fats = fats;
        return this;
    }

    public ProductBuilder setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
        return this;
    }

    public ProductBuilder setCreatedByRole(String createdByRole) {
        this.createdByRole = createdByRole;
        return this;
    }

    public Product build(){
        return new Product(id,dtCreate,dtUpdate,title,weight,calories,proteins,fats,carbohydrates,createdByRole);
    }
}
