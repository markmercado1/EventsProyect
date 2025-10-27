package com.emm.mseventoservice.feign;

import com.emm.mseventoservice.dtos.OrganizerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrganizerFeignClientFallback implements OrganizerFeignClient {
    
    @Override
    public OrganizerDTO getOrganizerById(Long organizerId) {
        log.error("Fallback: Unable to retrieve organizer with ID: {}", organizerId);
        return OrganizerDTO.builder()
                .organizerId(organizerId)
                .name("Organizer information unavailable")
                .email("N/A")
                .phone("N/A")
                .build();
    }
    
    @Override
    public Boolean existsById(Long organizerId) {
        log.error("Fallback: Unable to verify organizer existence with ID: {}", organizerId);
        return true; // Fallback permite la creación para evitar bloqueo total
    }
}