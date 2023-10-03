package by.it_akademy.fitness.builder;

import by.it_akademy.fitness.storage.entity.CompositionDish;
import by.it_akademy.fitness.storage.entity.Dish;

import java.util.List;
import java.util.UUID;

public class DishBuilder {
    
    private UUID id;
    private Long dtCreate;
    private Long dtUpdate;
    private String title;
    private List<CompositionDish> comDishDTO;
    private String createByRole;

    private DishBuilder() {
    }
    
    public static DishBuilder create(){
        return new DishBuilder();
    }

    public DishBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public DishBuilder setDtCreate(Long dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public DishBuilder setDtUpdate(Long dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public DishBuilder setTitle(String title) {
        this.title = title;
        return this;
    }


    public DishBuilder setCompositionDish(List<CompositionDish> comDishDTO) {
        this.comDishDTO = comDishDTO;
        return this;
    }

    public DishBuilder setCreateByRole(String createByRole) {
        this.createByRole = createByRole;
        return this;

    }

    public Dish build(){
        return new Dish(id,dtCreate,dtUpdate,title, comDishDTO,createByRole);
    }
}
