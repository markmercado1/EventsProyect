package upeu.mse_attendance.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upeu.mse_attendance.dto.RegistrationResponseDTO;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "ms-registration-service", path = "/registrations")
public interface RegistrationFeign {

    // ------------------------------------
    // 1. Buscar inscripción por ID
    // ------------------------------------
    @GetMapping("/{id}")
    @CircuitBreaker(name = "registroListarPorIdCB", fallbackMethod = "fallbackRegistro")
    RegistrationResponseDTO buscarPorId(@PathVariable Long id);

    default RegistrationResponseDTO fallbackRegistro(Long id, Throwable e) {
        RegistrationResponseDTO dto = new RegistrationResponseDTO();
        dto.setRegistrationId(id);
        dto.setEventId(null);
        dto.setParticipantId(null);
        dto.setRegistrationDate(null);
        dto.setStatus("NO-DISPO");
        dto.setQrCode("NO-DISPO");
        dto.setRequiresPayment(false);
        dto.setPaymentOrderId(null);
        return dto;
    }


    // ------------------------------------
    // 2. Listar inscripciones por evento
    // ------------------------------------
    @GetMapping("/event/{eventId}")
    @CircuitBreaker(name = "registroListarPorEventoCB", fallbackMethod = "fallbackPorEvento")
    List<RegistrationResponseDTO> buscarPorEvento(@PathVariable Long eventId);

    default List<RegistrationResponseDTO> fallbackPorEvento(Long eventId, Throwable e) {
        return Collections.emptyList(); // No caes el sistema
    }

}


//package upeu.mse_attendance.feign;
//
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import upeu.mse_attendance.dto.RegistrationResponseDTO;
//
//@FeignClient(name = "ms-registration-service", path = "/registrations")
//public interface RegistrationFeign {
//
//    @GetMapping("/{id}")
//    @CircuitBreaker(name = "registroListarPorIdCB", fallbackMethod = "fallbackRegistro")
//    RegistrationResponseDTO buscarPorId(@PathVariable Long id);
//
//    // Fallback correcto según tu DTO
//    default RegistrationResponseDTO fallbackRegistro(Long id, Throwable e) {
//        RegistrationResponseDTO dto = new RegistrationResponseDTO();
//        dto.setRegistrationId(id);
//        dto.setEventId(null);
//        dto.setParticipantId(null);
//        dto.setRegistrationDate(null);
//        dto.setStatus("NO-DISPO");
//        dto.setQrCode("NO-DISPO");
//        dto.setRequiresPayment(false);
//        dto.setPaymentOrderId(null);
//        return dto;
//    }
//}
