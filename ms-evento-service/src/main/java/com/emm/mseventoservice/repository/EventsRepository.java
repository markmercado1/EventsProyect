package com.emm.mseventoservice.repository;

import com.emm.mseventoservice.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Event, Long> {

}
