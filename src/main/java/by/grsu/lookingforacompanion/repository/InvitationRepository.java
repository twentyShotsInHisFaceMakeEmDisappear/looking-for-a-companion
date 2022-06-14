package by.grsu.lookingforacompanion.repository;

import by.grsu.lookingforacompanion.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
}
