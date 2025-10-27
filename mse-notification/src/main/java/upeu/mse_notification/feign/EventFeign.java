package upeu.mse_notification.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upeu.mse_notification.dto.EventDTO;

@FeignClient(name="ms-events-service", path = "/events")
public interface EventFeign {
    @GetMapping("/{id}")
    public EventDTO buscarPorId(@PathVariable Long id);
}
