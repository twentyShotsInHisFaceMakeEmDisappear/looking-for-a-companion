package by.grsu.lookingforacompanion.controller;

import by.grsu.lookingforacompanion.dto.AuthenticationDataDto;
import by.grsu.lookingforacompanion.dto.AuthenticationRequestDto;
import by.grsu.lookingforacompanion.service.AuthenticationServiceInterface;
import by.grsu.lookingforacompanion.util.logger.ProcessTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/security/")
public class AuthenticationController {

    private final AuthenticationServiceInterface authenticationService;

    @ProcessTrace
    @PostMapping("auth")
    public AuthenticationDataDto authentication(@RequestBody AuthenticationRequestDto authenticationRequestDto) {

        return authenticationService.authenticate(authenticationRequestDto);
    }

}
