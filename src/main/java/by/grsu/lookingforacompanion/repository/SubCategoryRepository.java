package by.grsu.lookingforacompanion.repository;

import by.grsu.lookingforacompanion.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    @Query(value = "select sc.id, sc.description, sc.image_url, sc.categories_id, " +
            "sc.short_description, sc.title from sub_categories sc " +
            "left join nodes n on sc.id = n.sub_categories_id " +
            "where n.id = :nodeId",
        nativeQuery = true)
    Optional<SubCategory> getSubCategoryByNodeId(Long nodeId);

    @Query(value = "select sc.id, sc.description, sc.image_url, sc.categories_id, " +
            "sc.short_description, sc.title from sub_categories sc " +
            "left outer join nodes n on sc.id = n.sub_categories_id " +
            "group by sc.id order by count(n) limit :limit",
        nativeQuery = true)
    List<SubCategory> getSubCategoriesByNodesCount(Integer limit);

    List<SubCategory> getSubCategoriesByCategoryOwnerId(Long categoryOwnerId);

}
