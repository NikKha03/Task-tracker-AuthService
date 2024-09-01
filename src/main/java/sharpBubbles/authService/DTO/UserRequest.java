package sharpBubbles.authService.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String email;

    private String tg;

    private String firstName;

    private String lastName;

}
