package by.grsu.lookingforacompanion.service;

import by.grsu.lookingforacompanion.dto.InformationResponseDto;
import by.grsu.lookingforacompanion.dto.InvitationRequestDto;

public interface InvitationServiceInterface {

    InformationResponseDto requestAnInvitation(InvitationRequestDto invitationRequestDto);

    InformationResponseDto deactivateAnInvitation(InvitationRequestDto invitationRequestDto);

}
