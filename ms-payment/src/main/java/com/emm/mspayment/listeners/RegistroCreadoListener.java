package com.emm.mspayment.listeners;

import com.emm.mspayment.events.RegistrationCreatedEvent;
import com.emm.mspayment.service.PaymentOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistroCreadoListener {

    private final PaymentOrderService paymentOrderService;

    @KafkaListener(topics = "registros-topic", groupId = "registro-group",containerFactory = "kafkaListenerContainerFactory")
    public void escucharRegistroCreado(RegistrationCreatedEvent event) {
        System.out.println("Evento recibido en ms-empleo: " + event);
    }
}