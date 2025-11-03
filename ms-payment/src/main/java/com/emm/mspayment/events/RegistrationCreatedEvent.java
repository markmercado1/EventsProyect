package com.emm.mspayment.events;

public record RegistrationCreatedEvent(
        Long registrationId,
        Long participantId,
        Long eventId,
        Boolean requiresPayment
) {}