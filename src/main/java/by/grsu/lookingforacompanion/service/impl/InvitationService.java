package by.grsu.lookingforacompanion.service.impl;

import by.grsu.lookingforacompanion.dto.InformationResponseDto;
import by.grsu.lookingforacompanion.dto.InvitationRequestDto;
import by.grsu.lookingforacompanion.entity.Invitation;
import by.grsu.lookingforacompanion.exception.invitation.AlreadyExistsByEmailException;
import by.grsu.lookingforacompanion.repository.CredentialRepository;
import by.grsu.lookingforacompanion.repository.InvitationRepository;
import by.grsu.lookingforacompanion.service.InvitationServiceInterface;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class InvitationService implements InvitationServiceInterface {

    private final ModelMapper mapper;

    private final InvitationRepository invitationRepository;

    private final CredentialRepository credentialRepository;

    @Override
    public InformationResponseDto requestAnInvitation(InvitationRequestDto invitationRequestDto) {
        if (credentialRepository.existsByEmail(invitationRequestDto.getEmail()))
            throw new AlreadyExistsByEmailException("Email already used!");

        Invitation invitationEntity = new Invitation().setEmail(invitationRequestDto.getEmail())
                .setExpirationTime(new Date(getExpirationTime().getTimeInMillis()))
                .setGeneratedCode("");

        invitationRepository.save(invitationEntity);

        return new InformationResponseDto().setMessage("Check your email, there is something there!");
    }

    private Calendar getExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Calendar.getInstance().getTime());
        calendar.add(Calendar.DATE, 7);

        return calendar;
    }

}
