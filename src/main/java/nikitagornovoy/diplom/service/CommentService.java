package nikitagornovoy.diplom.service;

import nikitagornovoy.diplom.domain.Comment;
import nikitagornovoy.diplom.domain.User;
import nikitagornovoy.diplom.domain.Views;
import nikitagornovoy.diplom.dto.EventType;
import nikitagornovoy.diplom.dto.ObjectType;
import nikitagornovoy.diplom.repo.CommentRepo;
import nikitagornovoy.diplom.repo.CommentRepo;
import nikitagornovoy.diplom.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class CommentService {
    private final CommentRepo commentRepo;
    private final BiConsumer<EventType, Comment> wsSender;

    @Autowired
    public CommentService(CommentRepo commentRepo, WsSender wsSender) {
        this.commentRepo = commentRepo;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
    }

    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);
        Comment commentFromDb = commentRepo.save(comment);

        wsSender.accept(EventType.CREATE, commentFromDb);

        return commentFromDb;
    }
}
