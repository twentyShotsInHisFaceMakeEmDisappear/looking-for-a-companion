package by.grsu.lookingforacompanion.service.impl;

import by.grsu.lookingforacompanion.dto.TruncatedUserDto;
import by.grsu.lookingforacompanion.dto.UserRegistrationInformationDto;
import by.grsu.lookingforacompanion.entity.Credential;
import by.grsu.lookingforacompanion.entity.User;
import by.grsu.lookingforacompanion.exception.common.PasswordMissMatchException;
import by.grsu.lookingforacompanion.exception.user.DisplayedNameCompromiseException;
import by.grsu.lookingforacompanion.exception.user.UserNotFoundException;
import by.grsu.lookingforacompanion.repository.CredentialRepository;
import by.grsu.lookingforacompanion.repository.UserRepository;
import by.grsu.lookingforacompanion.service.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

    private final ModelMapper mapper;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final CredentialRepository credentialRepository;

    @Value("${default.user.avatar}")
    private String defaultUserAvatar;

    @Override
    public TruncatedUserDto getTruncatedUserInformationById(Long userId) {
        User currentUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        return mapper.map(currentUser, TruncatedUserDto.class);
    }

    @Override
    public TruncatedUserDto registration(String email, UserRegistrationInformationDto userRegistrationInformationDto) {
        if (!userRegistrationInformationDto.getPassword()
                .equals(userRegistrationInformationDto.getPasswordRepetition()))
            throw new PasswordMissMatchException("Password miss match.");

        if (userRepository.existsByDisplayedName(userRegistrationInformationDto.getUsername()))
            throw new DisplayedNameCompromiseException("This username already used!");

        Credential currentCredential = new Credential().setEmail(email)
                .setPassword(passwordEncoder.encode(userRegistrationInformationDto.getPassword()));

        currentCredential = credentialRepository.save(currentCredential);

        User currentUser = new User().setBirthDate(userRegistrationInformationDto.getBirthDate())
                .setAvatarUrl(defaultUserAvatar)
                .setDisplayedName(userRegistrationInformationDto.getUsername())
                .setPhoneNumber(userRegistrationInformationDto.getPhoneNumber())
                .setCredentials(currentCredential);

        currentUser = userRepository.save(currentUser);

        return mapper.map(currentUser, TruncatedUserDto.class);
    }

}
