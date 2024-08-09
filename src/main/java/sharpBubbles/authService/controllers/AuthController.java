package sharpBubbles.authService.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/task-tracker/auth")
public class AuthController {
    @GetMapping("/checkAuth")
    public boolean isAuth(Principal principal) {
        return principal != null;
    }
}
