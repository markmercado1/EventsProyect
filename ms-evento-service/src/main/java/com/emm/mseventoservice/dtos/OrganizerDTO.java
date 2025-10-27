package com.emm.mseventoservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizerDTO {
    private Long organizerId;
    private String name;
    private String email;
    private String phone;
}