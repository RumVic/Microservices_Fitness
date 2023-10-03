package by.it_akademy.fitness.controller;

import by.it_akademy.fitness.idto.InputProfileDTO;
import by.it_akademy.fitness.odto.OutputProfileDTO;
import by.it_akademy.fitness.service.api.IProfileService;
import by.it_akademy.fitness.storage.entity.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("api/v1/profile")
@RequiredArgsConstructor
public class ProfileServlet {

    private final String CREATED = "New Profile was successfully created ";

    private final IProfileService service;

    @PostMapping
    protected ResponseEntity<String> post(@RequestBody InputProfileDTO idto, HttpServletRequest request) {
        final String authHeader = request.getHeader(AUTHORIZATION);
        Profile created = service.create(idto, authHeader);
        String uid = created.getId().toString();
        return new ResponseEntity<>(CREATED+"id : "+uid, HttpStatus.CREATED);
    }

    @GetMapping("/{uuid_profile}")
    protected ResponseEntity<OutputProfileDTO> getById(@PathVariable(name = "uuid_profile") UUID id) {
        return ResponseEntity.ok(service.readById(id));
    }


}

