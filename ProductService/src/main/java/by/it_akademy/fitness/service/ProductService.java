package by.it_akademy.fitness.service;

import by.it_akademy.fitness.builder.ProductBuilder;
import by.it_akademy.fitness.exception.LockException;
import by.it_akademy.fitness.idto.InputProductDTO;
import by.it_akademy.fitness.mappers.ProductMapper;
import by.it_akademy.fitness.odto.OutPage;
import by.it_akademy.fitness.odto.OutputProductDTO;
import by.it_akademy.fitness.security.filter.JwtUtil;
import by.it_akademy.fitness.service.api.IProductService;
import by.it_akademy.fitness.storage.api.IProductStorage;
import by.it_akademy.fitness.storage.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.persistence.OptimisticLockException;
import java.time.Clock;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ProductService implements IProductService {

    private final String CREATED = "Line in food journal was created";
    private final String UPDATED = "Line in food journal was updated";

    private final String EDITED = "Line already edited by somebody else";

    private final String LOCK = "Editing forbidden";

    private final IProductStorage storage;

    private final JwtUtil jwtUtil;
    private final ProductMapper productMapper;

    private final RestTemplate restTemplate;

    public ProductService(IProductStorage storage,
                          // TODO IUserService userService,
                          JwtUtil jwtUtil,
                          //TODO IAuditService auditService,
                          ProductMapper productMapper, RestTemplate restTemplate) {
        this.storage = storage;
        // TODO this.userService = userService;
        this.jwtUtil = jwtUtil;
        // TODO this.auditService = auditService;
        this.productMapper = productMapper;
        this.restTemplate = restTemplate;
    }

    // TODO private final IUserService userService;


    // TODO private final IAuditService auditService;

    @Override
    @Transactional
    public Product create(InputProductDTO idto, String header) {

        validate(idto);

//   TODO     String login = userService.extractCurrentToken(header);
//        String mail = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userService.loadCurrentUserByLogin(mail);


        Product product = storage.save(ProductBuilder
                .create()
                .setId(UUID.randomUUID())
                .setDtCreate(Clock.systemUTC().millis())
                .setDtUpdate(Clock.systemUTC().millis())
                .setTitle(idto.getTitle())
                .setWeight(idto.getWeight())
                .setCalories(idto.getCalories())
                .setProteins(idto.getProteins())
                .setFats(idto.getFats())
                .setCarbohydrates(idto.getCarbohydrates())
                // TODO .setCreatedByRole(login)
                .build());

//    TODO     auditService.create(
//                user,
//                EntityType.PRODUCT,
//                CREATED,
//                product.getId().toString()
//        );

        return product;
    }

    public Product read(UUID uuid) {
        return storage.findById(uuid).orElseThrow();
    }

    @Override
    public OutPage<OutputProductDTO> get(Pageable pag) {
        Page<Product> pageOfProduct = storage.findAll(pag);
//        String url = "http://localhost:8090/api/v1/event";
//        restTemplate.getForObject(url,String.class,CREATED);
        sendEventRequest(CREATED);
        return productMapper.map(pageOfProduct);
    }

    @Override
    @Transactional
    public Product update(UUID id, Long dtUpdate, InputProductDTO idto, String header) throws LockException {

        validate(idto);
        Product readed = storage.findById(id).orElseThrow();

        String mail = SecurityContextHolder.getContext().getAuthentication().getName();
        // TODO User user = userService.loadCurrentUserByLogin(mail);

//      TODO   if (!readed.getCreatedByRole().equals(user.getLogin())) {
//            if (!user.getRole().equals("ROLE_ADMIN")) {
//                throw new LockException(LOCK);
//            }
//        }
//
//        String login = userService.extractCurrentToken(header);

       /* if (readed == null) {
            throw new IllegalArgumentException("Product wasn't found");
        }*/


        if (!readed.getDtUpdate().equals(dtUpdate)) {
            throw new OptimisticLockException(EDITED);
        }

        Product productUpdate = storage.save(ProductBuilder
                .create()
                .setId(readed.getId())
                .setDtCreate(readed.getDtCreate())
                .setDtUpdate(Clock.systemUTC().millis())
                .setTitle(idto.getTitle())
                .setWeight(idto.getWeight())
                .setCalories(idto.getCalories())
                .setProteins(idto.getProteins())
                .setFats(idto.getFats())
                .setCarbohydrates(idto.getCarbohydrates())
                // TODO .setCreatedByRole(login)
                .build());
        // TODO auditService.create(user, EntityType.PRODUCT, UPDATED, productUpdate.getId().toString());
        return productUpdate;
    }

    @Override
    public void delete(Product product) {
    }

    public void validate(InputProductDTO idto) {
        if (idto == null) {
            throw new IllegalStateException("You didn't pass the product");
        }
        if (idto.getTitle() == null) {
            throw new IllegalStateException("You didn't pass the Title");
        }
        if (idto.getFats() < 0 || idto.getFats() == 0) {
            throw new IllegalStateException("You didn't pass the value of Fats");
        }
        if (idto.getCarbohydrates() < 0 || idto.getCarbohydrates() == 0) {
            throw new IllegalStateException("You didn't pass the value of Carbohydrates");
        }
        if (idto.getProteins()<0 || idto.getProteins() == 0){
            throw new IllegalStateException("You didn't pass the value of Proteins");
        }
        if (idto.getCalories()<0 || idto.getCalories() ==0){
            throw new IllegalStateException("You didn't pass the value of Calories");
        }
        if (idto.getWeight()<0 || idto.getWeight()==0){
            throw new IllegalStateException("You didn't pass the value of Weight");
        }
    }
    public void sendEventRequest(String description){

        String url = "http://localhost:8090/api/v1/event";//http://localhost:8090/api/v1/audit/event

        //http://localhost:8090/api/v1/event?description=checkDercrpt
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("description", description);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("Request was successful.");
        } else {
            System.out.println("Request failed with status code: " + responseEntity.getStatusCode());
        }
    }
}
