package sharpBubbles.authService.config.oauth2.userInfo;

import sharpBubbles.authService.models.RegistrationSource;
import sharpBubbles.authService.models.UserRole;

public interface OAuth2UserInfo {

    String getEmail();

    String getFirstName();

    String getLastName();

    UserRole getRole();

    RegistrationSource getRegistrationSource();

}
