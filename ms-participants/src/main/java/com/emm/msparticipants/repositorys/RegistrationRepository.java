package com.emm.msparticipants.repositorys;

import com.emm.msparticipants.models.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByEventId(Long eventId);
    List<Registration> findByParticipant_ParticipantId(Long participantId);
}
