package upeu.mse_notification.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upeu.mse_notification.dto.RegistrationResponseDTO;

@FeignClient(
        name="ms-registrations-service",
        path = "/registrations",
        contextId = "registrationFeignClient"
)
public interface RegistrationFeign {

    @GetMapping("/{id}")
    upeu.mse_notification.dto.RegistrationResponseDTO getRegistrationById(@PathVariable("id") Long id);
}
