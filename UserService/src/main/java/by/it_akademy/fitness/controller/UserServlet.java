package by.it_akademy.fitness.controller;

import by.it_akademy.fitness.idto.InputUserDTO;
import by.it_akademy.fitness.odto.OutputUserDTO;
import by.it_akademy.fitness.service.UserService;
import by.it_akademy.fitness.security_module.configuration.config.SecurityConfig;
import by.it_akademy.fitness.security_module.configuration.filter.JwtUtil;
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
//@NoArgsConstructor
public class UserServlet {
    private final String CREATED = "The new User was created and waiting activation , please check your Email";

    @Autowired
    public UserServlet(AuthenticationManager authenticationManager, SecurityConfig securityConfig, UserService service, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.securityConfig = securityConfig;
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    private final AuthenticationManager authenticationManager;
    private final SecurityConfig securityConfig;
    private final UserService service;
    private final JwtUtil jwtUtil;
    //TODO private final MailService mailService;

    @PostMapping("/registration")
    public ResponseEntity<String> mailRegistration(@RequestBody @Valid InputUserDTO inputUserDTO) {
        //TODO mailService.addUser(inputUserDTO);
        return new ResponseEntity<>(CREATED, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginIn(@RequestBody InputUserDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword()));

        UserDetails userDetails = service.loadUserByLogin(request.getMail());
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
