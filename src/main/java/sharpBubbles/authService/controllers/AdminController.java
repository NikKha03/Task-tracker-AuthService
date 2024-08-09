package sharpBubbles.authService.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sharpBubbles.authService.models.User;
import sharpBubbles.authService.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/task-tracker/admin")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAllUsers();
    }
}
