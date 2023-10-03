package by.it_akademy.fitness.controller;

import by.it_akademy.fitness.idto.InputUserDTO;
import by.it_akademy.fitness.odto.OutputUserDTO;
import by.it_akademy.fitness.security.filter.JwtUtil;
import by.it_akademy.fitness.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserServlet {
    private final String CREATED = "The new User was created and waiting activation , please check your Email";
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final UserService service;
    @Autowired
    private final JwtUtil jwtUtil;

    @Autowired
    //TODO private final MailService mailService;

    @PostMapping("/registration")
    public ResponseEntity<String> mailRegistration(@RequestBody @Valid  InputUserDTO inputUserDTO) {
        //TODO mailService.addUser(inputUserDTO);
        return new ResponseEntity<>(CREATED, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginIn(@RequestBody InputUserDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword()));
        final UserDetails userDetails = service.loadUserByLogin(request.getMail());
        if (userDetails != null) {
            return ResponseEntity.ok(jwtUtil.generateToken(userDetails, request.getMail()));
        }
        return ResponseEntity.status(400).body("Some error has occurred");
    }

    @GetMapping("/me")
    public ResponseEntity<OutputUserDTO> getMe(HttpServletRequest request) {
        final String authHeader = request.getHeader(AUTHORIZATION);
        return ResponseEntity.ok(service.getMyInfo(authHeader));
    }

}
