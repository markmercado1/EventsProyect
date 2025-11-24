package com.emm.msregistrations.dtos;

import lombok.Data;

@Data
public class NotificationCreateDTO {

    private Long participantId;

    private Long eventId;

    private String templateCode;
}
