package by.it_akademy.fitness.builder;

import by.it_akademy.fitness.storage.entity.DiaryFood;
import by.it_akademy.fitness.storage.entity.Dish;
import by.it_akademy.fitness.storage.entity.Product;

import java.util.UUID;

public class DiaryFoodBuilder {
    
    private UUID id;
    
    private Long dtCreate;
    
    private Long dtUpdate;
    
    private Long dtSupply;
    
    private Dish dish;
   
    private double weightDish;
    
    private Product product;
    
    private double weightProduct;

    private UUID profile;
    
    private DiaryFoodBuilder() {
    }
    
    public static DiaryFoodBuilder create() {
        return new DiaryFoodBuilder();
    }

    public DiaryFoodBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public DiaryFoodBuilder setDtCreate(Long dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public DiaryFoodBuilder setDtUpdate(Long dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public DiaryFoodBuilder setDtSupply(Long dtSupply) {
        this.dtSupply = dtSupply;
        return this;
    }

    public DiaryFoodBuilder setDish(Dish dish) {
        this.dish = dish;
        return this;
    }

    public DiaryFoodBuilder setWeightDish(double weightDish) {
        this.weightDish = weightDish;
        return this;
    }

    public DiaryFoodBuilder setProduct(Product product) {
        this.product = product;
        return this;
    }

    public DiaryFoodBuilder setWeightProduct(double weightProduct) {
        this.weightProduct = weightProduct;
        return this;
    }

    public DiaryFoodBuilder setProfile(UUID  profile) {
        this.profile = profile;
        return this;
    }

    public DiaryFood build(){
        return new DiaryFood(id,dtCreate,dtUpdate,dtSupply,dish,weightDish,product,weightProduct,profile);
    }

}

