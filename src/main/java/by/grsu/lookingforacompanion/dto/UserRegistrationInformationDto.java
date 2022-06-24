package by.grsu.lookingforacompanion.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Date;

@Data
@JsonAutoDetect
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserRegistrationInformationDto {

    private Date birthDate;

    private String username;

    private String password;

    private String phoneNumber;

    private String passwordRepetition;

}
