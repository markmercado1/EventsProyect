package com.emm.msregistrations.feign;

import com.emm.msregistrations.dtos.NotificationCreateDTO;
import com.emm.msregistrations.dtos.NotificationResponseLocalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mse-notification", path = "/notifications")
public interface NotificationFeign {
    @PostMapping("/send")
    NotificationResponseLocalDTO sendNotification(@RequestBody NotificationCreateDTO request);
}
