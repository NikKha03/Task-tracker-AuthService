package sharpBubbles.authService.config.oauth2.userInfo;

import sharpBubbles.authService.models.RegistrationSource;
import sharpBubbles.authService.models.UserRole;

import java.util.Map;

public class YandexOauth2UserInfo implements OAuth2UserInfo {

    private final Map<String, Object> attributes;

    public YandexOauth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("default_email");
    }

    @Override
    public String getFirstName() {
        return (String) attributes.get("first_name");
    }

    @Override
    public String getLastName() {
        return (String) attributes.get("last_name");
    }

    @Override
    public UserRole getRole() {
        return UserRole.ROLE_USER;
    }

    @Override
    public RegistrationSource getRegistrationSource() {
        return RegistrationSource.YANDEX;
    }

}
