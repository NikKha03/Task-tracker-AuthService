package sharpBubbles.authService.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sharpBubbles.authService.DTO.CategoryRequest;
import sharpBubbles.authService.models.Category;
import sharpBubbles.authService.models.User;
import sharpBubbles.authService.service.CategoryService;
import sharpBubbles.authService.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/task-tracker/category")
public class CategoryController {
    public CategoryController(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

    private final UserService userService;
    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(Principal principal, @RequestBody CategoryRequest request) {
        User user = userService.findUserByEmail(principal.getName()).orElse(null);
//        User user = userService.findUserByEmail("nik.kh.03@yandex.ru").orElse(null);

        categoryService.createCategory(user, request.getName());

        return ResponseEntity.ok(user.getCategories().stream().map(Category::getName));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(Principal principal) {
        System.out.println(principal);
//        return ResponseEntity.ok("");
        User user = userService.findUserByEmail(principal.getName()).orElse(null);
        return ResponseEntity.ok(user.getCategories().stream().map(Category::getName));
    }

}
