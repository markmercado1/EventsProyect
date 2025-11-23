package upeu.mse_notification.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upeu.mse_notification.dto.ParticipantResponseDTO;

@FeignClient(name="ms-participants-service", path = "/participants")
public interface ParticipantFeign {

    @GetMapping("/{id}")
    ParticipantResponseDTO getParticipantById(@PathVariable("id") Long id);
}


//package upeu.mse_notification.feign;
//
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import upeu.mse_notification.dto.ParticipantResponseDTO;
//
//@FeignClient(name="ms-participants-service", path = "/participants")
//public interface ParticipantFeign {
//    @GetMapping("/{id}")
//    @CircuitBreaker(name = "participanteListarPorIdCB", fallbackMethod = "fallbackParticipant")
//    ParticipantResponseDTO buscarPorId(@PathVariable Long id);
//
//    default ParticipantResponseDTO fallbackParticipant(Long id, Exception e) {
//        ParticipantResponseDTO participantDTO = new ParticipantResponseDTO();
//        participantDTO.setIdParticipant(id); // Mantener el id solicitado
//        participantDTO.setFirstName("Participante no disponible");
//        participantDTO.setLastName("");
//        participantDTO.setEmail("");
//        participantDTO.setPhone("");
//        participantDTO.setRegistrationDate(null);
//        return participantDTO;
//    }
//}
