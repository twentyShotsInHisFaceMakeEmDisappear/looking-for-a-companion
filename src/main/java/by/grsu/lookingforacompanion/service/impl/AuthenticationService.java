package by.grsu.lookingforacompanion.service.impl;

import by.grsu.lookingforacompanion.dto.AuthenticationDataDto;
import by.grsu.lookingforacompanion.dto.AuthenticationRequestDto;
import by.grsu.lookingforacompanion.entity.Credential;
import by.grsu.lookingforacompanion.exception.user.UserNotFoundException;
import by.grsu.lookingforacompanion.repository.CredentialRepository;
import by.grsu.lookingforacompanion.security.jwt.token.JwtTokenProvider;
import by.grsu.lookingforacompanion.service.AuthenticationServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationServiceInterface {

    private final JwtTokenProvider tokenProvider;

    private final BCryptPasswordEncoder passwordEncoder;

    private final CredentialRepository credentialRepository;

    private final AuthenticationManager authenticationManager;

    @PostConstruct
    public void init() {
        System.out.println(passwordEncoder.encode("msa2002"));
    }

    @Override
    public AuthenticationDataDto authenticate(AuthenticationRequestDto authenticationRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequestDto.getEmail(), authenticationRequestDto.getPassword()));

        Credential currentCredential = credentialRepository.getCredentialByEmail(authenticationRequestDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Incorrect email or password."));

        return AuthenticationDataDto.builder().build()
                .setStatus(HttpStatus.OK.value())
                .setUsername(currentCredential.getUser().getDisplayedName())
                .setToken(tokenProvider.createToken(authenticationRequestDto.getEmail(),
                        currentCredential.getRoles().stream().toList()));
    }

}
