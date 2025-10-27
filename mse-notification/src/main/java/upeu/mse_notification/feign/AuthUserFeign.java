package upeu.mse_notification.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upeu.mse_notification.dto.AuthUserDTO;

@FeignClient(name="ms-auth-service", path = "/auth")
public interface AuthUserFeign {
    @GetMapping("/{id}")
    public AuthUserDTO buscarPorId(@PathVariable int id);
}