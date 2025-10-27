package com.emm.mseventoservice.feign;

import com.emm.mseventoservice.dtos.OrganizerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "organizer-service",
    url = "${organizer.service.url:http://localhost:8081}",
    fallback = OrganizerFeignClientFallback.class
)
public interface OrganizerFeignClient {
    
    @GetMapping("/api/organizers/{organizerId}")
    OrganizerDTO getOrganizerById(@PathVariable("organizerId") Long organizerId);
    
    @GetMapping("/api/organizers/{organizerId}/exists")
    Boolean existsById(@PathVariable("organizerId") Long organizerId);
}