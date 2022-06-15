package by.grsu.lookingforacompanion.service.impl;

import by.grsu.lookingforacompanion.dto.InformationResponseDto;
import by.grsu.lookingforacompanion.dto.InvitationRequestDto;
import by.grsu.lookingforacompanion.entity.Invitation;
import by.grsu.lookingforacompanion.exception.invitation.AlreadyExistsByEmailException;
import by.grsu.lookingforacompanion.exception.invitation.InvitationAlreadyDeactivatedException;
import by.grsu.lookingforacompanion.exception.invitation.NoSuchInvitationException;
import by.grsu.lookingforacompanion.repository.CredentialRepository;
import by.grsu.lookingforacompanion.repository.InvitationRepository;
import by.grsu.lookingforacompanion.service.InvitationServiceInterface;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class InvitationService implements InvitationServiceInterface {

//    private final JavaMailSender javaMailSender;

    private final InvitationRepository invitationRepository;

    private final CredentialRepository credentialRepository;

    @Override
    public InformationResponseDto requestAnInvitation(InvitationRequestDto invitationRequestDto) {
        if (credentialRepository.existsByEmail(invitationRequestDto.getEmail()) ||
                invitationRepository.existsByEmail(invitationRequestDto.getEmail()))
            throw new AlreadyExistsByEmailException("Email already used!");

        Invitation invitationEntity = new Invitation().setEmail(invitationRequestDto.getEmail())
                .setExpirationTime(new Date(getExpirationTime().getTimeInMillis()))
                .setGeneratedCode("");

        invitationRepository.save(invitationEntity);

        //sendEmailInvitation(invitationEntity);

        return new InformationResponseDto().setMessage("Check your email, there is something there!");
    }

    @Override
    public InformationResponseDto deactivateAnInvitation(InvitationRequestDto invitationRequestDto) {
        if (!invitationRepository.isActiveByEmail(invitationRequestDto.getEmail()))
            throw new InvitationAlreadyDeactivatedException("This invitation already deactivated.");

        invitationRepository.deactivateAtInvitation(invitationRequestDto.getEmail());

        return new InformationResponseDto().setMessage("Invitation " + invitationRequestDto.getEmail() +
                " successfully deactivated!");
    }

    private void sendEmailInvitation(Invitation invitation) {
        Thread parallelEmailSendingThread = new Thread(() -> {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setFrom("noreply@compan.era");
            simpleMailMessage.setTo(invitation.getEmail());

            simpleMailMessage.setSubject("CompanEra: Hey there! This is your invitation mail!");
            simpleMailMessage.setText("\tTo join the CompanEra family just click at the link below!" +
                    "\n<a href=\"http://localhost:8080/invite/ci?" + invitation.getGeneratedCode() + "\">CLICK HERE</a>");

//            javaMailSender.send(simpleMailMessage);
        });

        parallelEmailSendingThread.start();
    }

    private Calendar getExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Calendar.getInstance().getTime());
        calendar.add(Calendar.DATE, 7);

        return calendar;
    }

}
