package e1i4.petmily.users.dto;

import e1i4.petmily.users.domain.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private UserRole role;
    private String name;
    private String username;
}
