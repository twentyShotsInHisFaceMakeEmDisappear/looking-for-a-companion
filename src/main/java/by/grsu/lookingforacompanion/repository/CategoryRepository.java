package by.grsu.lookingforacompanion.repository;

import by.grsu.lookingforacompanion.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> getCategoryById(Long id);

    List<Category> getCategoryByTitleStartsWith(String title);

    @Query(value = "select categ.id, categ.title, categ.short_description, " +
            "categ.image_url, categ.description from categories categ " +
            "left join sub_categories sc on categ.id = sc.categories_id " +
            "left outer join nodes n on sc.id = n.sub_categories_id " +
            "group by categ.id order by count(n) desc limit :limit",
            nativeQuery = true)
    List<Category> getCategoriesBySubCategoriesNodesCountLimitedBy(Integer limit);

}
