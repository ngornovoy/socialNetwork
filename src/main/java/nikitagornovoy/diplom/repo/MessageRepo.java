package nikitagornovoy.diplom.repo;

import nikitagornovoy.diplom.domain.Message;
import nikitagornovoy.diplom.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.domain.Pageable;

import java.util.List;


public interface MessageRepo extends JpaRepository<Message, Long> {
    @EntityGraph(attributePaths = { "comments" })
    Page<Message> findByAuthorIn(List<User> users, Pageable pageable);
    @EntityGraph(attributePaths = { "comments" })
    Page<Message> findAll(Pageable pageable);
}