package by.it_akademy.fitness.service;

import by.it_akademy.fitness.builder.CompositionDishBuilder;
import by.it_akademy.fitness.exception.LockException;
import by.it_akademy.fitness.idto.InputComDishDTO;
import by.it_akademy.fitness.odto.OutPage;
import by.it_akademy.fitness.service.api.ICompositionDishService;
import by.it_akademy.fitness.service.api.IProductService;
import by.it_akademy.fitness.storage.api.ICompositionDishStorage;
import by.it_akademy.fitness.storage.entity.CompositionDish;
import by.it_akademy.fitness.storage.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CompositionDishService implements ICompositionDishService {

    public CompositionDishService(ICompositionDishStorage storage, IProductService service) {
        this.storage = storage;
        this.service = service;
    }

    private final ICompositionDishStorage storage;

    private final IProductService service;


    @Override
    @Transactional
    public List<CompositionDish> create(List<InputComDishDTO> list, UUID idDish) {

        List<CompositionDish> compositionDishes = new ArrayList<>();

        for (InputComDishDTO inputComDishDTO : list) {
           Product product = service.read(inputComDishDTO.getProduct().getId());
            UUID idCD = UUID.randomUUID();
            CompositionDish compositionDish = storage.save(CompositionDishBuilder
                    .create()
                    .setId(idCD)
                    .setDtCreate(Clock.systemUTC().millis())
                    .setDtUpdate((Clock.systemUTC().millis()))
                    .setTitle(inputComDishDTO.getTitle())
                    .setDish(idDish)
                    .setProduct(product)
                    .setWeight(inputComDishDTO.getWeight())
                    .build());
            compositionDishes.add(read(idCD));
        }
        return compositionDishes;
    }

    @Override
    public CompositionDish create(InputComDishDTO dto, String header) {
        return null;
    }

    @Override
    public CompositionDish read(UUID id) {
        return storage.findById(id).orElseThrow();
    }

    @Override
    public OutPage get(Pageable pageable) {
        return null;
    }

    @Override
    public List<CompositionDish> update(List<InputComDishDTO> item, UUID idDish) {

        List<CompositionDish> list = new ArrayList<>();

        for (InputComDishDTO dto : item) {

            UUID uuid = UUID.randomUUID();

            CompositionDish created = storage.save(CompositionDishBuilder
                    .create()
                    .setId(uuid)
                    .setDtCreate(Clock.systemUTC().millis())
                    .setDtUpdate((Clock.systemUTC().millis()))
                    .setTitle(dto.getTitle())
                    .setDish(idDish)
                    .setProduct(dto.getProduct())
                    .setWeight(dto.getWeight())
                    .build());

            list.add(storage.findById(uuid).orElseThrow());
        }
        return list;
    }

    @Override
    public CompositionDish update(UUID id, Long dtUpdate, InputComDishDTO item, String header) throws LockException {
        return null;
    }


    @Override
    @Transactional
    public void delete(List<CompositionDish> compositionDish) {
        for (CompositionDish beingDeleted : compositionDish) {
            storage.deleteById(beingDeleted.getId());
        }
    }

    @Override
    @Transactional
    public void delete(CompositionDish compositionDish) {
    }
}
