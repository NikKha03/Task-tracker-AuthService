package sharpBubbles.authService.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sharpBubbles.authService.models.User;
import sharpBubbles.authService.repository.UserRepository;
import sharpBubbles.authService.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Long getPrincipalUserId(Principal principal) {
        User currentUser = userRepository.findByEmail(principal.getName()).orElse(null);
        return currentUser != null ? currentUser.getUserId() : null;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
