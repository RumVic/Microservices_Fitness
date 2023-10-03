package by.it_akademy.fitness.service;

import by.it_akademy.fitness.builder.DiaryFoodBuilder;
import by.it_akademy.fitness.exception.LockException;
import by.it_akademy.fitness.idto.InputDiaryFoodDTO;
import by.it_akademy.fitness.mappers.DiaryFoodMapper;
import by.it_akademy.fitness.odto.OutPage;
import by.it_akademy.fitness.odto.OutputDiaryFoodDTO;
import by.it_akademy.fitness.service.api.*;
import by.it_akademy.fitness.storage.api.IDiaryFoodStorage;
import by.it_akademy.fitness.storage.entity.*;
import by.it_akademy.fitness.util.enams.EntityType;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.List;
import java.util.UUID;


@Service
@Transactional(readOnly = true)
public class DiaryFoodService implements IDiaryFoodService {

    private final String CREATED = "Line in food journal was created";
    private final String LOCK = "You can't create the line ";

    private final Long STARTAPP = 1674752530161L;

    public DiaryFoodService(// TODO IUserService userService,
                            IDiaryFoodStorage storage,
                            IDishService serviceDish,
                            IProductService productService,
                            // TODO IAuditService auditService,
                            // TODO IProfileService profileService,
                            DiaryFoodMapper diaryFoodMapper) {
        // TODO this.userService = userService;
        this.storage = storage;
        this.serviceDish = serviceDish;
        this.productService = productService;
        // TODO this.auditService = auditService;
        // TODO this.profileService = profileService;
        this.diaryFoodMapper = diaryFoodMapper;
    }

    // TODO private final IUserService userService;

    private final IDiaryFoodStorage storage;

    private final IDishService serviceDish;


    private final IProductService productService;

    // TODO private final IAuditService auditService;

    // TODO private final IProfileService profileService;


    private final DiaryFoodMapper diaryFoodMapper;


    @Override
    @Transactional
    public DiaryFood createWithParam(InputDiaryFoodDTO dto, String header, UUID uuid) throws LockException {
        validateDiary(dto);

        Dish readedDish = null;
        Product readedProduct= null;

//        TODO UUID uid = userService.extractCurrentUUID(header);
//        TODO String mail = SecurityContextHolder.getContext().getAuthentication().getName();
//        TODO User user = userService.loadCurrentUserByLogin(mail);
//        TODO Profile profile = profileService.read(uuid);

        if(dto.getDish()==null){
        Product readProduct = productService.read(dto.getProduct().getId());
        readedProduct = readProduct;
        }

        if(dto.getProduct()==null){
            Dish readDish = serviceDish.read(dto.getDish().getId());
            readDish=readDish;
        }

        //Product readedProduct = productService.read(dto.getProduct().getId());


//        TODO if (!user.getId().equals(profile.getUser().getId())) {
//        TODO     throw new LockException(LOCK);
//        TODO }

        DiaryFood diaryFood = storage.save(DiaryFoodBuilder
                .create()
                .setId(UUID.randomUUID())
                .setDtCreate(Clock.systemUTC().millis())
                .setDtUpdate(Clock.systemUTC().millis())
                .setDtSupply(dto.getDtSupply())
                .setDish(readedDish)
                .setWeightDish(dto.getWeightDish())
                .setProduct(readedProduct)
                .setWeightProduct(dto.getWeightProduct())
                .setProfile(uuid)
                .build());

//        TODO auditService.create(
//        TODO         user,
//        TODO         EntityType.DIARY_FOOD,
//        TODO         CREATED,
//        TODO         diaryFood.getId().toString()
//        TODO );

        return diaryFood;
    }


    @Override
    public DiaryFood read(UUID id) {
        return storage.findById(id).orElseThrow();
    }

    @Override
    public OutPage<OutputDiaryFoodDTO> get(Pageable pageable, UUID id) {
        Page<DiaryFood> page = storage.findAllByProfile(pageable, id);
        return diaryFoodMapper.map(page);
    }

    @Override
    public List<DiaryFood> getListOfLine(UUID id) {
        return null; //storage.findByProfile(id);
    }

    @Override
    public OutPage get(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional
    public DiaryFood update(UUID id, Long dtUpdate, InputDiaryFoodDTO item, String header) throws LockException {
        return null;
    }

    @Override
    public void delete(DiaryFood diaryFood) {
    }

    @Override
    @Transactional
    public DiaryFood create(InputDiaryFoodDTO dto, String header) {
        return null;
    }

    void validateDiary(InputDiaryFoodDTO dto) {
        if (dto.getDish() == null && dto.getProduct() == null) {
            throw new IllegalStateException("You need to pass Product or Dish");
        }
        if (dto.getWeightProduct() == 0 && dto.getWeightDish() == 0) {
            throw new IllegalStateException("You need to pass weight of food");
        }
        if (dto.getDtSupply() < STARTAPP) {
            throw new IllegalStateException("State valid value of meal time");
        }
    }
}
