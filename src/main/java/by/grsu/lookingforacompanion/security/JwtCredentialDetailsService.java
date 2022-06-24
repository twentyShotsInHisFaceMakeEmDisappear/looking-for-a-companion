package by.grsu.lookingforacompanion.security;

import by.grsu.lookingforacompanion.entity.Credential;
import by.grsu.lookingforacompanion.repository.CategoryRepository;
import by.grsu.lookingforacompanion.repository.CredentialRepository;
import by.grsu.lookingforacompanion.security.jwt.user.JwtUserFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JwtCredentialDetailsService implements UserDetailsService {

    private final CredentialRepository credentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credential user = credentialRepository.getCredentialByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found"));

        return JwtUserFactory.jwtUserCreate(user);
    }

}
