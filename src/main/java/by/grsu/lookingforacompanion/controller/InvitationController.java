package by.grsu.lookingforacompanion.controller;

import by.grsu.lookingforacompanion.dto.InformationResponseDto;
import by.grsu.lookingforacompanion.dto.InvitationRequestDto;
import by.grsu.lookingforacompanion.service.InvitationServiceInterface;
import by.grsu.lookingforacompanion.util.logger.ProcessTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/invite")
public class InvitationController {

    private final InvitationServiceInterface invitationService;

    @ProcessTrace
    @PostMapping("/rl")
    public InformationResponseDto getInvitationLink(@RequestBody InvitationRequestDto invitationRequest) {

        return invitationService.requestAnInvitation(invitationRequest);
    }

}
