package by.grsu.lookingforacompanion.controller;

import by.grsu.lookingforacompanion.dto.TruncatedUserDto;
import by.grsu.lookingforacompanion.service.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserServiceInterface userService;

    @GetMapping("/t/{userId}")
    public TruncatedUserDto getTruncatedUserById(@PathVariable Long userId) {

        return userService.getTruncatedUserInformationById(userId);
    }

}
