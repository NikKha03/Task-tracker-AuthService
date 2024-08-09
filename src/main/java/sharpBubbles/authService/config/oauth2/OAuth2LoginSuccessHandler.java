package sharpBubbles.authService.config.oauth2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import sharpBubbles.authService.config.oauth2.userInfo.OAuth2UserInfo;
import sharpBubbles.authService.config.oauth2.userInfo.YandexOauth2UserInfo;
import sharpBubbles.authService.models.User;
import sharpBubbles.authService.models.UserBuilder;
import sharpBubbles.authService.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserService userService;

    @Value("${frontend.user-page}")
    private String frontendUserPage;

    public OAuth2LoginSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        this.setAlwaysUseDefaultTargetUrl(true);
        DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = principal.getAttributes();

        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

        if ("yandex".equals(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId())) {
            final String nameAttributeKey = "default_email";

            userService.findUserByEmail((String) attributes.get(nameAttributeKey))
                    .ifPresentOrElse(user -> {
                        try {
                            authenticateOauth2User(user, attributes, nameAttributeKey, oAuth2AuthenticationToken, response);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(SecurityContextHolder.getContext().getAuthentication());
                    }, () -> {
                        User user = buildOauth2User(new YandexOauth2UserInfo(attributes));
                        userService.saveUser(user);
                        try {
                            authenticateOauth2User(user, attributes, nameAttributeKey, oAuth2AuthenticationToken, response);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }

    private User buildOauth2User(OAuth2UserInfo oAuth2UserInfo) {
        return new UserBuilder()
                .setEmail(oAuth2UserInfo.getEmail())
                .setFirstName(oAuth2UserInfo.getFirstName())
                .setLastName(oAuth2UserInfo.getLastName())
                .setRole(oAuth2UserInfo.getRole())
                .setRegistrationSource(oAuth2UserInfo.getRegistrationSource())
                .build();
    }

    private void authenticateOauth2User(User user, Map<String, Object> attributes, String nameAttributeKey, OAuth2AuthenticationToken oAuth2AuthenticationToken, HttpServletResponse response) throws IOException {
        DefaultOAuth2User defaultOAuth2User = new DefaultOAuth2User(List.of(new SimpleGrantedAuthority(user.getRoles().name())),
                attributes, nameAttributeKey);
        Authentication securityAuth = new OAuth2AuthenticationToken(defaultOAuth2User, List.of(new SimpleGrantedAuthority(user.getRoles().name())),
                oAuth2AuthenticationToken.getAuthorizedClientRegistrationId());
        SecurityContextHolder.getContext().setAuthentication(securityAuth);
        response.sendRedirect(frontendUserPage);
    }
}
