package by.grsu.lookingforacompanion.repository;

import by.grsu.lookingforacompanion.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByDisplayedName(String displayedName);

}
