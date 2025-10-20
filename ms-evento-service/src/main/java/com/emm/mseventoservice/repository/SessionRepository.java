package com.emm.mseventoservice.repository;

import com.emm.mseventoservice.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByEvent_EventId(Long eventId);

}
