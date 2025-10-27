package upeu.mse_notification.repository;


import upeu.mse_notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Custom query method
    List<Notification> findByUserId(String userId);
}
