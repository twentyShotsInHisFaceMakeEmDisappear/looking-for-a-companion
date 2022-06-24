package by.grsu.lookingforacompanion.service;

import by.grsu.lookingforacompanion.dto.TruncatedUserDto;
import by.grsu.lookingforacompanion.dto.UserRegistrationInformationDto;

public interface UserServiceInterface {

    TruncatedUserDto getTruncatedUserInformationById(Long userId);

    TruncatedUserDto registration(String email, UserRegistrationInformationDto userRegistrationInformationDto);

}
