package by.it_akademy.fitness.controller;

import by.it_akademy.fitness.exception.LockException;
import by.it_akademy.fitness.idto.InputDiaryFoodDTO;
import by.it_akademy.fitness.odto.OutPage;
import by.it_akademy.fitness.odto.OutputDiaryFoodDTO;
import by.it_akademy.fitness.service.api.IDiaryFoodService;
import by.it_akademy.fitness.storage.entity.DiaryFood;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/v1/profile")
public class DiaryFoodServlet {
    public DiaryFoodServlet(IDiaryFoodService service) {
        this.service = service;
    }

    private final IDiaryFoodService service;

    private final String CREATED = "The line in your dairy was successfully created";


    @PostMapping("/{uuid_profile}/journal/food")
    protected ResponseEntity<String > post(@RequestBody @Valid InputDiaryFoodDTO idto,
                                             HttpServletRequest request,
                                             @PathVariable(name = "uuid_profile") UUID id) throws LockException {

        final String authHeader = request.getHeader(AUTHORIZATION);
        DiaryFood created = this.service.createWithParam(idto,authHeader,id);
        return new ResponseEntity<>(CREATED, HttpStatus.CREATED);
    }

    @GetMapping("/{uuid_profile}/journal/food")
    protected ResponseEntity<OutPage> getList(
            @PathVariable(name = "uuid_profile") UUID id,
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size);
        OutPage<OutputDiaryFoodDTO> diary = service.get(pageable,id);
        return new ResponseEntity<>(diary,HttpStatus.OK);

    }

}
