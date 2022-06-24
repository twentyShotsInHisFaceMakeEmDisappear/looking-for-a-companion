package by.grsu.lookingforacompanion.security.jwt.user;

import by.grsu.lookingforacompanion.entity.Credential;
import by.grsu.lookingforacompanion.entity.Role;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class JwtUserFactory {

    private final String ROLE_PREFIX = "ROLE_";

    public User jwtUserCreate(Credential credential) {
        return new User(
                credential.getEmail(),
                credential.getPassword(),
                mapToGrantedAuthority(new HashSet<>(credential.getRoles())));
    }

    public List<GrantedAuthority> mapToGrantedAuthority(Set<Role> roleSet) {
        return roleSet.stream()
                .map(role ->
                        new SimpleGrantedAuthority(ROLE_PREFIX + role.getTitle()))
                .collect(Collectors.toList());

    }

}
