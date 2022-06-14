package by.grsu.lookingforacompanion.repository;

import by.grsu.lookingforacompanion.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credential, Long> {

    Boolean existsByEmail(String email);

}
