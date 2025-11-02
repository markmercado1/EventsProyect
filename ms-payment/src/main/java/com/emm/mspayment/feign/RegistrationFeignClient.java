package com.emm.mspayment.feign;



import com.emm.mspayment.dto.RegistrationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "registration-service", url = "${registration.service.url}")
public interface RegistrationFeignClient {
    
    @GetMapping("/api/registrations/{id}")
    RegistrationResponseDTO getRegistrationById(@PathVariable("id") Long id);
}