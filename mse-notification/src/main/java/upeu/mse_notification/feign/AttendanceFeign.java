package upeu.mse_notification.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upeu.mse_notification.dto.AttendanceResponseDTO;

@FeignClient(
        name="ms-attendance-service",
        path = "/attendances",
        contextId = "attendanceFeignClient"
)
public interface AttendanceFeign {

    @GetMapping("/{id}")
    AttendanceResponseDTO getAttendanceById(@PathVariable("id") Long id);
}
