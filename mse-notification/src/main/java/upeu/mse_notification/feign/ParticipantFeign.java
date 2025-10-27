package upeu.mse_notification.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upeu.mse_notification.dto.ParticipantDTO;

@FeignClient(name="ms-participants-service", path = "/participants")
public interface ParticipantFeign {
    @GetMapping("/{id}")
    public ParticipantDTO buscarPorId(@PathVariable Long id);
}
