package by.it_akademy.fitness.mail.controller;


import by.it_akademy.fitness.mail.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor

public class MailController {

    public final MailSender mailSender;



    @PostMapping("/activation")
    public ResponseEntity<String> sendActivation(
            @RequestBody
            @RequestParam String email, String activationCode, String message) {
        mailSender.send(email, activationCode, message);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
