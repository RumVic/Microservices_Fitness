package by.it_akademy.fitness.service.api;

import by.it_akademy.fitness.idto.InputComDishDTO;
import by.it_akademy.fitness.odto.OutputComDishDTO;
import by.it_akademy.fitness.storage.entity.CompositionDish;

import java.util.List;
import java.util.UUID;

public interface ICompositionDishService extends IService <CompositionDish, InputComDishDTO, OutputComDishDTO>{

    List<CompositionDish> create(List<InputComDishDTO> dto, UUID idDish);

    List<CompositionDish> update(List<InputComDishDTO> dto, UUID idDish);

    void delete(List<CompositionDish> compositionDish);
}
