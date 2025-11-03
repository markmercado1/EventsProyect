package com.emm.msregistrations.events;

public record RegistrationCreatedEvent(
        Long registrationId,
        Long participantId,
        Long eventId,
        Boolean requiresPayment
) {}