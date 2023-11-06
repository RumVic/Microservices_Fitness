package by.it_akademy.fitness.mappers;

import by.it_akademy.fitness.odto.OutPage;
import by.it_akademy.fitness.odto.OutputComDishDTO;
import by.it_akademy.fitness.odto.OutputDishDTO;
import by.it_akademy.fitness.storage.entity.CompositionDish;
import by.it_akademy.fitness.storage.entity.Dish;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class DishMapper {

    public OutPage<OutputDishDTO> map(Page<Dish> dishes) {

        OutPage<OutputDishDTO> outPage = new OutPage<>();

        outPage.setNumber(dishes.getNumber());
        outPage.setSize(dishes.getSize());
        outPage.setTotalPages(dishes.getTotalPages());
        outPage.setTotalElements((int) dishes.getTotalElements());
        outPage.setFirst(dishes.isFirst());
        outPage.setNumberOfElements(dishes.getNumberOfElements());
        outPage.setLast(dishes.isLast());

        List<OutputDishDTO> list = new ArrayList<>();

        for (Dish dish : dishes.getContent()) {
            OutputDishDTO dto = builderDish(dish);
            list.add(dto);
        }
        outPage.setContent(list);
        return outPage;
    }

    public OutputDishDTO builderDish(Dish dish) {
        OutputDishDTO dto = new OutputDishDTO();
        dto.setId(dish.getId());
        dto.setDtCreate(dish.getDtCreate());
        dto.setDtUpdate(dish.getDtUpdate());
        dto.setTitle(dish.getTitle());
        dto.setCreateByRole(dish.getCreateByRole());

        List<CompositionDish> comDish = dish.getCompositionDishList();
        dto.setCompositionDishes(builderOutComDishList(comDish));

        return dto;
    }

    public List<OutputComDishDTO> builderOutComDishList(List<CompositionDish> comDish) {

        List<OutputComDishDTO> listComDish = new ArrayList<>();

        for (CompositionDish comDishD : comDish) {
            OutputComDishDTO outDto = builderOutComDish(comDishD);
            listComDish.add(outDto);
        }
        return listComDish;
    }

    public OutputComDishDTO builderOutComDish(CompositionDish comDish) {
        OutputComDishDTO dto = new OutputComDishDTO();
        dto.setWeight(comDish.getWeight());
        ProductMapper productMapper = new ProductMapper();
        dto.setProduct(productMapper.fromEntityToOutput(comDish.getProduct()));
        return dto;
    }
}
