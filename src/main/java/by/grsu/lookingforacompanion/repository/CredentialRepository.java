package by.grsu.lookingforacompanion.repository;

import by.grsu.lookingforacompanion.entity.Credential;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Long> {

    Boolean existsByEmail(String email);

    @EntityGraph(attributePaths = { "roles", "user" })
    Optional<Credential> getCredentialByEmail(String email);

}
