package nikitagornovoy.diplom.repo;

import nikitagornovoy.diplom.domain.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}