package sharpBubbles.authService.service;

import sharpBubbles.authService.models.Category;
import sharpBubbles.authService.models.User;

public interface CategoryService {

    Category createCategory(User user, String categoryName);

    void deleteCategory(Long categoryId);

    Category findByName(String name);

    Boolean existsByName(String categoryName);

}
