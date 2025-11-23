package upeu.mse_notification.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upeu.mse_notification.dto.EventResponseDTO;

@FeignClient(
        name="ms-events-service",
        path = "/events",
        contextId = "eventFeignClient"
)
public interface EventFeign {

    @GetMapping("/{id}")
    EventResponseDTO getEventById(@PathVariable("id") Long id);
}


//package upeu.mse_notification.feign;
//
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import upeu.mse_notification.dto.EventResponseDTO;
//
//@FeignClient(name="ms-events-service", path = "/events", contextId = "eventFeignClient")
//public interface EventFeign {
//    @GetMapping("/{id}")
//    @CircuitBreaker(name = "eventoListarPorIdCB", fallbackMethod = "fallbackEvent")
//    EventResponseDTO buscarPorId(@PathVariable Long id);
//
//    default EventResponseDTO fallbackEvent(Long id, Exception e) {
//        EventResponseDTO eventDTO = new EventResponseDTO();
//        eventDTO.setIdEvento(id);
//        eventDTO.setName("Evento no disponible");
//        eventDTO.setDescription("No se pudo recuperar la información del evento");
//        // Opcionalmente puedes inicializar otros campos mínimos
//        return eventDTO;
//    }
//}
