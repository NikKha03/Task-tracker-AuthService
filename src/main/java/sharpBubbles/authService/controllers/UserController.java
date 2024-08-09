package sharpBubbles.authService.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sharpBubbles.authService.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/task-tracker/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<Long> getUserTasks(Principal principal) {
        Long currentUserId = userService.getPrincipalUserId(principal);
        return ResponseEntity.ok(currentUserId);
    }
}
