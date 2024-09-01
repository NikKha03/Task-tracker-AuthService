package sharpBubbles.authService.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sharpBubbles.authService.DTO.UserRequest;
import sharpBubbles.authService.models.User;
import sharpBubbles.authService.models.UserBuilder;
import sharpBubbles.authService.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/task-tracker/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/userInfo")
    public ResponseEntity<User> getUserInfo(Principal principal) {
        User user = userService.findUserByEmail(principal.getName()).orElse(null);

        return ResponseEntity.ok(user);
    }

    @PutMapping("/changeInfo")
    public ResponseEntity<User> changeUserInfo(Principal principal, @RequestBody UserRequest request) {
        User user = userService.findUserByEmail(principal.getName()).orElse(null);
        UserBuilder userBuilder = new UserBuilder(user)
                .setEmail(request.getEmail())
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .setTg(request.getTg());

        return ResponseEntity.ok(userService.saveUser(userBuilder.build()));
    }
}
