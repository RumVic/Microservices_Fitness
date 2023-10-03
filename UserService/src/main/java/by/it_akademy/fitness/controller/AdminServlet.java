package by.it_akademy.fitness.controller;

import by.it_akademy.fitness.exception.LockException;
import by.it_akademy.fitness.idto.InputUserByAdmin;
import by.it_akademy.fitness.odto.OutPage;
import by.it_akademy.fitness.odto.OutputUserDTO;
import by.it_akademy.fitness.security.filter.JwtUtil;
import by.it_akademy.fitness.service.UserService;
import by.it_akademy.fitness.storage.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
@RequestMapping("api/v1")
public class AdminServlet {

    private final String CREATED = " USER with admin role created new USER";

    private final String UPDATED = " USER with admin role updated USER";


    private final UserService service;
    private final JwtUtil jwtUtil;

    @Autowired
    public AdminServlet(UserService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/users")
    public ResponseEntity<String> registrationByAdmin(@RequestBody @Valid InputUserByAdmin inputUserDTO) {
        UserDetails created = this.service.createNewUser(inputUserDTO);
        return new  ResponseEntity<>(CREATED, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<OutPage> getUsers(HttpServletRequest request,
                                            @RequestParam int size,
                                            @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.get(pageable));
    }

    @GetMapping("/users/{uuid}")
    public ResponseEntity<OutputUserDTO> getUserById(@PathVariable(name = "uuid") UUID uuid) {
        return ResponseEntity.ok(service.readInput(uuid));
    }

    @PutMapping("/users/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<String> updateUser(@RequestBody @Valid InputUserByAdmin idto,
                                           HttpServletRequest request,
                                           @PathVariable(name = "uuid") UUID id,
                                           @PathVariable(name = "dt_update") Long dtUpdate) throws LockException {
        final String authHeader = request.getHeader(AUTHORIZATION);
        User updateUser = service.update(id, dtUpdate, idto, authHeader);
        return new  ResponseEntity<>(UPDATED,HttpStatus.OK);
    }
}
