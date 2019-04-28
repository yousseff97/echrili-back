package murraco.repository;

import murraco.model.Notification;
import murraco.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {


    public List<Notification> getAllByUser(User user);


}
