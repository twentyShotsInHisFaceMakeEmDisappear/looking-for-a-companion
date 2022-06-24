package by.grsu.lookingforacompanion.service;

import by.grsu.lookingforacompanion.dto.AuthenticationDataDto;
import by.grsu.lookingforacompanion.dto.AuthenticationRequestDto;

public interface AuthenticationServiceInterface {

    AuthenticationDataDto authenticate(AuthenticationRequestDto authenticationRequestDto);

}
