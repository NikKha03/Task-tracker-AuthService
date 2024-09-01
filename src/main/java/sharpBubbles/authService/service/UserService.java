package sharpBubbles.authService.service;

import sharpBubbles.authService.models.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserByEmail(String email);

    List<User> findAllUsers();

    Long getPrincipalUserId(Principal principal);

    User saveUser(User user);

    boolean existsUserByEmail(String email);

}
