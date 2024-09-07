package sharpBubbles.authService.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sharpBubbles.authService.models.Category;
import sharpBubbles.authService.models.User;
import sharpBubbles.authService.repository.CategoryRepository;
import sharpBubbles.authService.service.CategoryService;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public Category createCategory(User user, String categoryName) {
        if (repository.existsByName(categoryName)) {
            if (!repository.findByName(categoryName).getUsers().contains(user)) {
                Category category = repository.findByName(categoryName);
                List<User> usersInCategory = category.getUsers();
                usersInCategory.add(user);
                category.setUsers(usersInCategory);

                return repository.save(category);
            }
            return null;
        }

        List<User> users = new ArrayList<>();
        users.add(user);

        Category category = new Category();
        category.setName(categoryName);
        category.setUsers(users);

        return repository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {

    }

    @Override
    public Category findByName(String name) {
        return null;
    }

    @Override
    public Boolean existsByName(String categoryName) {
        return null;
    }
}
