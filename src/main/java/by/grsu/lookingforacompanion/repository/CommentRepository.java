package by.grsu.lookingforacompanion.repository;

import by.grsu.lookingforacompanion.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select count(comm) from comments comm " +
            "where comm.nodes_id = :nodeId",
        nativeQuery = true)
    Integer getCommentsCountByNodeId(Long nodeId);

    List<Comment> getCommentsByNodeId(Long nodeId);

    List<Comment> getCommentsByAuthorId(Long authorId);

}
