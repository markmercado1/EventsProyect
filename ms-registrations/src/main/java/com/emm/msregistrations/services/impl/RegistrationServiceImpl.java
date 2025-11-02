package com.emm.msregistrations.services.impl;


import com.emm.msregistrations.dtos.*;
import com.emm.msregistrations.enums.RegistrationStatus;
import com.emm.msregistrations.exceptions.DuplicateRegistrationException;
import com.emm.msregistrations.exceptions.ResourceNotFoundException;
import com.emm.msregistrations.feign.EventFeign;
import com.emm.msregistrations.feign.ParticipantFeign;
import com.emm.msregistrations.feign.PaymentFeign;
import com.emm.msregistrations.mappers.RegistrationMapper;
import com.emm.msregistrations.models.Registration;
import com.emm.msregistrations.repositorys.RegistrationRepository;
import com.emm.msregistrations.services.RegistrationService;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RegistrationMapper registrationMapper;
    private final EventFeign eventFeign;
    private final ParticipantFeign participantFeign;
    private final PaymentFeign paymentFeign;

    @Override
    public RegistrationResponseDTO createRegistration(CreateRegistrationDTO dto) {
        log.info("Creando registro para evento {} y participante {}", dto.getEventId(), dto.getParticipantId());

        // Validar que el evento existe
        validateEventExists(dto.getEventId());

        // Validar que el participante existe
        validateParticipantExists(dto.getParticipantId());

        // Validar que no existe un registro duplicado
        if (registrationRepository.existsByEventIdAndParticipantId(dto.getEventId(), dto.getParticipantId())) {
            throw new DuplicateRegistrationException(
                    "Ya existe un registro para este participante en el evento especificado"
            );
        }

        // Validar orden de pago si requiere pago
        if (Boolean.TRUE.equals(dto.getRequiresPayment()) && dto.getPaymentOrderId() != null) {
            validatePaymentOrderExists(dto.getPaymentOrderId());
        }

        Registration registration = registrationMapper.toEntity(dto);
        Registration savedRegistration = registrationRepository.save(registration);

        log.info("Registro creado exitosamente con ID: {}", savedRegistration.getRegistrationId());

        return enrichResponseDTO(registrationMapper.toResponseDTO(savedRegistration));
    }

    @Override
    public RegistrationResponseDTO updateRegistration(Long id, UpdateRegistrationDTO dto) {
        log.info("Actualizando registro con ID: {}", id);

        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro no encontrado con ID: " + id));

        // Validar orden de pago si se actualiza
        if (dto.getPaymentOrderId() != null) {
            validatePaymentOrderExists(dto.getPaymentOrderId());
        }

        registrationMapper.updateEntity(dto, registration);
        Registration updatedRegistration = registrationRepository.save(registration);

        log.info("Registro actualizado exitosamente");

        return enrichResponseDTO(registrationMapper.toResponseDTO(updatedRegistration));
    }

    @Override
    public RegistrationResponseDTO getRegistrationById(Long id) {
        log.info("Buscando registro con ID: {}", id);

        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro no encontrado con ID: " + id));

        return enrichResponseDTO(registrationMapper.toResponseDTO(registration));
    }

    @Override
    public List<RegistrationResponseDTO> getAllRegistrations() {
        log.info("Obteniendo todos los registros");

        return registrationRepository.findAll().stream()
                .map(registrationMapper::toResponseDTO)
                .map(this::enrichResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RegistrationResponseDTO> getRegistrationsByEventId(Long eventId) {
        log.info("Buscando registros para el evento: {}", eventId);

        validateEventExists(eventId);

        return registrationRepository.findByEventId(eventId).stream()
                .map(registrationMapper::toResponseDTO)
                .map(this::enrichResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RegistrationResponseDTO> getRegistrationsByParticipantId(Long participantId) {
        log.info("Buscando registros para el participante: {}", participantId);

        validateParticipantExists(participantId);

        return registrationRepository.findByParticipantId(participantId).stream()
                .map(registrationMapper::toResponseDTO)
                .map(this::enrichResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RegistrationResponseDTO> getRegistrationsByStatus(RegistrationStatus status) {
        log.info("Buscando registros con estado: {}", status);

        return registrationRepository.findByStatus(status).stream()
                .map(registrationMapper::toResponseDTO)
                .map(this::enrichResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRegistration(Long id) {
        log.info("Eliminando registro con ID: {}", id);

        if (!registrationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Registro no encontrado con ID: " + id);
        }

        registrationRepository.deleteById(id);
        log.info("Registro eliminado exitosamente");
    }

    @Override
    public String generateQRCode(Long registrationId) {
        log.info("Generando código QR para registro: {}", registrationId);

        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new ResourceNotFoundException("Registro no encontrado con ID: " + registrationId));

        String qrCode = UUID.randomUUID().toString();
        registration.setQrCode(qrCode);
        registrationRepository.save(registration);

        log.info("Código QR generado exitosamente");
        return qrCode;
    }

    // ========== Métodos auxiliares ==========

    private void validateEventExists(Long eventId) {
        try {
            eventFeign.getEventById(eventId);
            log.debug("Evento validado: {}", eventId);
        } catch (FeignException.NotFound e) {
            log.error("Evento no encontrado: {}", eventId);
            throw new ResourceNotFoundException("Evento no encontrado con ID: " + eventId);
        } catch (FeignException e) {
            log.error("Error al validar evento: {}", e.getMessage());
            throw new RuntimeException("Error al comunicarse con el servicio de eventos", e);
        }
    }

    private void validateParticipantExists(Long participantId) {
        try {
            participantFeign.getParticipantById(participantId);
            log.debug("Participante validado: {}", participantId);
        } catch (FeignException.NotFound e) {
            log.error("Participante no encontrado: {}", participantId);
            throw new ResourceNotFoundException("Participante no encontrado con ID: " + participantId);
        } catch (FeignException e) {
            log.error("Error al validar participante: {}", e.getMessage());
            throw new RuntimeException("Error al comunicarse con el servicio de participantes", e);
        }
    }

    private void validatePaymentOrderExists(Long paymentOrderId) {
        try {
            paymentFeign.getPaymentOrderById(paymentOrderId);
            log.debug("Orden de pago validada: {}", paymentOrderId);
        } catch (FeignException.NotFound e) {
            log.error("Orden de pago no encontrada: {}", paymentOrderId);
            throw new ResourceNotFoundException("Orden de pago no encontrada con ID: " + paymentOrderId);
        } catch (FeignException e) {
            log.error("Error al validar orden de pago: {}", e.getMessage());
            throw new RuntimeException("Error al comunicarse con el servicio de pagos", e);
        }
    }

    private RegistrationResponseDTO enrichResponseDTO(RegistrationResponseDTO dto) {
        try {
            // Obtener información del evento
            EventDTO event = eventFeign.getEventById(dto.getEventId());
            dto.setEvent(event);
        } catch (FeignException e) {
            log.warn("No se pudo obtener información del evento: {}", e.getMessage());
        }

        try {
            // Obtener información del participante
            ParticipantDTO participant = participantFeign.getParticipantById(dto.getParticipantId());
            dto.setParticipant(participant);
        } catch (FeignException e) {
            log.warn("No se pudo obtener información del participante: {}", e.getMessage());
        }

        try {
            // Obtener información de la orden de pago si existe
            if (dto.getPaymentOrderId() != null) {
                PaymentOrderDTO paymentOrder = paymentFeign.getPaymentOrderById(dto.getPaymentOrderId());
                dto.setPaymentOrder(paymentOrder);
            }
        } catch (FeignException e) {
            log.warn("No se pudo obtener información de la orden de pago: {}", e.getMessage());
        }

        return dto;
    }
}