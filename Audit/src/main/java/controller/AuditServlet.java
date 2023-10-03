package controller;

import lombok.RequiredArgsConstructor;
import odto.OutPage;
import odto.OutputAuditDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.api.IAuditService;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class AuditServlet {

    public final IAuditService service;

    @GetMapping("/audit")
    public ResponseEntity<OutPage> getPage(@RequestParam int size,
                                           @RequestParam int page) {

        Pageable pageable = PageRequest.of(page, size);
        OutPage<OutputAuditDTO> outPage = service.get(pageable);
        return new ResponseEntity<>(outPage, HttpStatus.OK);
    }

    @GetMapping("/audit/{uuid}")
    public ResponseEntity<? extends List<OutputAuditDTO>> getUserById(@PathVariable(name = "uuid") String uuid) {
        return new ResponseEntity<>(service.getById(uuid), HttpStatus.OK);

    }
}
