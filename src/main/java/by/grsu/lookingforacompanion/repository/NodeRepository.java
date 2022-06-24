package by.grsu.lookingforacompanion.repository;

import by.grsu.lookingforacompanion.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NodeRepository extends JpaRepository<Node, Long> {

    List<Node> getNodesBySubCategoryId(Long subCategoryId);

    List<Node> getNodesByTitleContainingIgnoreCase(String title);

    List<Node> getNodesBySubCategoryCategoryOwnerId(Long subCategoryCategoryOwnerId);

}
