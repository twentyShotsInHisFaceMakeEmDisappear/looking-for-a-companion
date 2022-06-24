package by.grsu.lookingforacompanion.repository;

import by.grsu.lookingforacompanion.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select * from categories",
            nativeQuery = true)
    Set<Category> getAll();

    Optional<Category> getCategoryById(Long id);

    List<Category> getCategoriesByTitleContainingIgnoreCase(String title);

    @Query(value = "select categ.id, categ.title, categ.short_description, " +
            "categ.image_url, categ.description from categories categ " +
            "left join sub_categories sc on categ.id = sc.categories_id " +
            "left outer join nodes n on sc.id = n.sub_categories_id " +
            "group by categ.id order by count(n) desc limit :limit",
            nativeQuery = true)
    List<Category> getCategoriesBySubCategoriesNodesCountLimitedBy(Integer limit);

    List<Category> getCategoriesByDescriptionContainingIgnoreCase(String description);

    List<Category> getCategoriesByShortDescriptionContainingIgnoreCase(String shortDescription);

}
