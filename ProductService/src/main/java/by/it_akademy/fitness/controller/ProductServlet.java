package by.it_akademy.fitness.controller;

import by.it_akademy.fitness.idto.InputProductDTO;
import by.it_akademy.fitness.security_module.odto.OutPage;
import by.it_akademy.fitness.service.api.IProductService;
import by.it_akademy.fitness.storage.entity.Product;
import by.it_akademy.fitness.security_module.configuration.filter.JwtUtil;
import by.it_akademy.fitness.security_module.exceptionEdvice.LockException;
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
@RequestMapping("/api/v1/product")
public class ProductServlet {
    public ProductServlet(IProductService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    private final IProductService service;

    private final JwtUtil jwtUtil;

    private final String CREATED = "The Product was successfully added to library";

    private final String UPDATED = "The Product was successfully updated ";

    @GetMapping()
    protected ResponseEntity<OutPage> getList(
            @RequestParam int page,
            @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        OutPage products = service.get(pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //TODO : authorization part of this
    // methods are transferred to the UserService
    @PostMapping
    protected ResponseEntity<String> createProduct(@RequestBody @Valid InputProductDTO idto, HttpServletRequest request) {
        final String authHeader = request.getHeader(AUTHORIZATION);
        Product created = this.service.create(idto, authHeader);
        return new ResponseEntity<>(CREATED, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    protected ResponseEntity<String> doPut(@PathVariable(name = "uuid") UUID id,
                                           @PathVariable(name = "dt_update") Long dt_update,
                                           @RequestBody @Valid InputProductDTO idto,
                                           HttpServletRequest request) throws LockException {
        final String authHeader = request.getHeader(AUTHORIZATION);
        service.update(id, dt_update, idto, authHeader);
        return ResponseEntity.ok(UPDATED);
    }
}
