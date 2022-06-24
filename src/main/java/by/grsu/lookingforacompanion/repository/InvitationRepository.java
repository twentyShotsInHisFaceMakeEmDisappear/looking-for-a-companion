package by.grsu.lookingforacompanion.repository;

import by.grsu.lookingforacompanion.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    Boolean existsByEmail(String email);

    @Query(value = "select inv.isActive from invitations inv " +
            "where inv.email = :email",
        nativeQuery = true)
    Boolean isActiveByEmail(String email);

    @Modifying
    @Query(value = "update invitations inv set inv.isActive = false " +
            "where inv.email = :email",
            nativeQuery = true)
    void deactivateAtInvitation(String email);

    Optional<Invitation> getInvitationByEmail(String email);

}
