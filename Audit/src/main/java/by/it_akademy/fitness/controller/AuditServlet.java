package by.it_akademy.fitness.controller;

import by.it_akademy.fitness.enams.EntityType;
import by.it_akademy.fitness.idto.InputDTO;
import by.it_akademy.fitness.odto.OutPage;
import by.it_akademy.fitness.odto.OutputAuditDTO;
import by.it_akademy.fitness.service.api.IAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuditServlet {
    public final IAuditService service;
    @GetMapping("/audit")//{page}/{size}
    public ResponseEntity<OutPage> getPage(@RequestParam int size, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, size);
        OutPage<OutputAuditDTO> outPage = service.get(pageable);
        return new ResponseEntity<>(outPage, HttpStatus.OK);
    }
//    @GetMapping("/audit/{uuid}")
//    public ResponseEntity<? extends List<OutputAuditDTO>> getUserById
//            (@PathVariable(name = "uuid") String uuid) {
//        return new ResponseEntity<>(service.getById(uuid), HttpStatus.OK);
//    }
    @PostMapping("/event")
    public ResponseEntity<String> putEvent(@RequestBody InputDTO description){
        service.create(description.getUserId()
                ,description.getEntityType()
                ,description.getDescription()
                ,description.getEntityId());
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
