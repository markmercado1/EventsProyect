package upeu.mse_notification.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upeu.mse_notification.dto.ParticipantResponseDTO;

@FeignClient(name="ms-participants-service", path = "/participants", contextId="participantFeign")
public interface ParticipantFeign {

    @GetMapping("/{id}")
    ParticipantResponseDTO getParticipantById(@PathVariable("id") Long id);


}

