package sharpBubbles.authService.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sharpBubbles.authService.models.User;
import sharpBubbles.authService.repository.UserRepository;

import java.security.Principal;
import java.util.List;
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
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Long getPrincipalUserId(Principal principal) {
        User currentUser = userRepository.findByEmail(principal.getName()).orElse(null);
        return currentUser != null ? currentUser.getUserId() : null;
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
