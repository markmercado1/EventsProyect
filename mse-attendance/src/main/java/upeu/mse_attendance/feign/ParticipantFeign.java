package upeu.mse_attendance.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upeu.mse_attendance.dto.ParticipantDTO;

@FeignClient(name="ms-participant-service", path = "/participants")
public interface ParticipantFeign {
    @GetMapping("/{id}")
    public ParticipantDTO buscarPorId(@PathVariable Long id);
}
