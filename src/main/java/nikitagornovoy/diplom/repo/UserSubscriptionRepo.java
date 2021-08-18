package nikitagornovoy.diplom.repo;

import nikitagornovoy.diplom.domain.User;
import nikitagornovoy.diplom.domain.UserSubscription;
import nikitagornovoy.diplom.domain.UserSubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSubscriptionRepo extends JpaRepository<UserSubscription, UserSubscriptionId> {
    List<UserSubscription> findBySubscriber(User user);

    List<UserSubscription> findByChannel(User channel);

    UserSubscription findByChannelAndSubscriber(User channel, User subscriber);
}
