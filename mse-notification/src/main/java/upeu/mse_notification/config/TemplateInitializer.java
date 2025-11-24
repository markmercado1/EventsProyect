package upeu.mse_notification.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import upeu.mse_notification.entity.NotificationTemplate;
import upeu.mse_notification.repository.NotificationTemplateRepository;

@Component
@RequiredArgsConstructor
public class TemplateInitializer implements CommandLineRunner {

    private final NotificationTemplateRepository templateRepository;

    @Override
    public void run(String... args) throws Exception {
        if(templateRepository.findByCodeAndIsActiveTrue("PARTICIPANT_WELCOME").isEmpty()) {
            NotificationTemplate template = NotificationTemplate.builder()
                    .code("PARTICIPANT_WELCOME")
                    .name("Bienvenida al participante")
                    .channel("EMAIL")
                    .subject("¡Hola {participantName}! Bienvenido al evento {eventName}")
                    .body("Hola {participantName},<br>Te confirmamos tu inscripción al evento <b>{eventName}</b> que se realizará en {eventAddress}. ¡Nos vemos allí!")
                    .isActive(true)
                    .build();
            templateRepository.save(template);
        }
    }
}

