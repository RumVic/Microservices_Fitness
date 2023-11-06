package by.it_akademy.fitness.service;


import by.it_akademy.fitness.builder.DishBuilder;
import by.it_akademy.fitness.exception.LockException;
import by.it_akademy.fitness.idto.InputComDishDTO;
import by.it_akademy.fitness.idto.InputDishDTO;
import by.it_akademy.fitness.mappers.DishMapper;
import by.it_akademy.fitness.odto.OutPage;
import by.it_akademy.fitness.service.api.ICompositionDishService;
import by.it_akademy.fitness.service.api.IDishService;
import by.it_akademy.fitness.storage.api.IDishStorage;
import by.it_akademy.fitness.storage.entity.CompositionDish;
import by.it_akademy.fitness.storage.entity.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OptimisticLockException;
import java.time.Clock;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class DishService implements IDishService {

    public DishService(IDishStorage storage,
                       // TODO IUserService userService,
                       ICompositionDishService service,
                       // TODO IAuditService auditService,
                       DishMapper dishMapper) {
        this.storage = storage;
        //TODO this.userService = userService;
        this.service = service;
        //TODO this.auditService = auditService;
        this.dishMapper = dishMapper;
    }

    private final String CREATED = "Line in food journal was created";

    private final String UPDATED = "Line in food journal was updated";

    private final String EDITED = "Line already edited by somebody else";

    private final String LOCK = "Editing forbidden";


    private final IDishStorage storage;

    // TODO private final IUserService userService;
    private final ICompositionDishService service;

    // TODO private final IAuditService auditService;

    private final DishMapper dishMapper;

   /* public DishService(IDishStorage dishStorage, ICompositionDishService service) {
        this.storage = dishStorage;
        this.service = service;
    }*/

    @Override
    @Transactional
    public Dish create(InputDishDTO idto, String header) {

        validateDish(idto);

        // TODO String login = userService.extractCurrentToken(header);

        List<InputComDishDTO> list = idto.getComDishDTO();

        UUID idDish = UUID.randomUUID();

        String mail = SecurityContextHolder.getContext().getAuthentication().getName();
        // TODO User user = userService.loadCurrentUserByLogin(mail);

        Dish dish = storage.save(DishBuilder
                .create()
                .setId(idDish)
                .setDtCreate(Clock.systemUTC().millis())
                .setDtUpdate(Clock.systemUTC().millis())
                .setTitle(idto.getTitle())
                .setCompositionDish(service.create(list, idDish))
                // TODO .setCreateByRole(login)
                .build()
        );

        /* TODO auditService.create(
                user,
                EntityType.DISH,
                CREATED,
                dish.getId().toString());

         */
        return dish;
    }
    public Dish read(UUID uuid) {
        return storage.findById(uuid).orElseThrow();
    }



    @Override
    public OutPage get(Pageable pageable) {
        Page<Dish> dishPage = storage.findAll(pageable);
        return dishMapper.map(dishPage) ;
    }

    @Override
    @Transactional
    public Dish update(UUID id, Long dtUpdate, InputDishDTO idto,String header) throws LockException {

        validateDish(idto);
        // TODO String login = userService.extractCurrentToken(header);

        String mail = SecurityContextHolder.getContext().getAuthentication().getName();
        // TODO User user = userService.loadCurrentUserByLogin(mail);

        Dish readed = storage.findById(id).orElseThrow();
        List<CompositionDish> readedList = readed.getCompositionDishList();

//        TODO if (!readed.getCreateByRole().equals(user.getLogin())) {
//        TODO     if (!user.getRole().equals("ROLE_ADMIN")) {
//        TODO         throw new LockException(LOCK);
//        TODO     }
//        TODO }
        if (!readed.getDtUpdate().equals(dtUpdate)) {
            throw new OptimisticLockException(EDITED);
        }

        service.delete(readedList);

        List<InputComDishDTO> list = idto.getComDishDTO();

        Dish dishUpdate = storage.save(DishBuilder
                .create()
                .setId(id)
                .setDtCreate(readed.getDtCreate())
                .setDtUpdate(Clock.systemUTC().millis())
                .setCompositionDish((service.update(list, id)))
                .setTitle(idto.getTitle())
                // TODO .setCreateByRole(login)
                .build());

        // TODO auditService.create(user, EntityType.DISH, UPDATED, dishUpdate.getId().toString());

        return dishUpdate;
    }

    void validateDish(InputDishDTO inputDishDTO){
        List <InputComDishDTO> dtoList = inputDishDTO.getComDishDTO();
        for (InputComDishDTO dto: dtoList) {
            if (dto.getDish()==null && dto.getProduct()==null){
                throw new IllegalStateException("You need to pass something");
            }
            if (dto.getWeight()<0 || dto.getWeight()==0){
                throw new IllegalArgumentException("Weight no can have this value");
            }
        }
    }

    @Override
    public void delete(Dish dish) {
    }
}

