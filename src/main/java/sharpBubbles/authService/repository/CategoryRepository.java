package sharpBubbles.authService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sharpBubbles.authService.models.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    boolean existsByName(String categoryName);

}
