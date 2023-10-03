package by.it_akademy.fitness.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/activation")
@RequiredArgsConstructor
public class ActivationServlet {

    private final String CREATED = "Congratulations on your successful registration";
    // TODO private final MailService mailService;

    @GetMapping("/{activation_code}/mail/{mail}")
    public ResponseEntity<String> getUserById(@PathVariable(name = "activation_code") String code,
                                              @PathVariable(name ="mail") String mail) throws IOException {
                //TODO mailService.activateUser(code);
        return new ResponseEntity<>(CREATED, HttpStatus.OK);
    }
}
