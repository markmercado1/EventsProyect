package upeu.mse_notification.repository;

import upeu.mse_notification.entity.NotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate, Long> {

    // Buscar plantilla por nombre
    Optional<NotificationTemplate> findByName(String name);
}
