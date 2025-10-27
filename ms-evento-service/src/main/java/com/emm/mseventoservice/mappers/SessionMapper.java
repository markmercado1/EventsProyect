package com.emm.mseventoservice.mappers;

import com.emm.mseventoservice.dtos.CreateSessionDTO;
import com.emm.mseventoservice.dtos.SessionDTO;
import com.emm.mseventoservice.models.Event;
import com.emm.mseventoservice.models.Session;
import org.springframework.stereotype.Component;

@Component // 👈 clave: lo registra como bean Spring
public class SessionMapper {

    public SessionDTO toDto(Session session) {
        if (session == null) {
            return null;
        }

        return SessionDTO.builder()
                .sessionId(session.getSessionId())
                .eventId(session.getEvent() != null ? session.getEvent().getEventId() : null)
                .title(session.getTitle())
                .dateTime(session.getDateTime())
                .durationMinutes(session.getDurationMinutes())
                .speaker(session.getSpeaker())
                .build();
    }

    public Session toEntityFromCreateDto(CreateSessionDTO createDto) {
        if (createDto == null) {
            return null;
        }

        Session session = Session.builder()
                .title(createDto.getTitle())
                .dateTime(createDto.getDateTime())
                .durationMinutes(createDto.getDurationMinutes())
                .speaker(createDto.getSpeaker())
                .build();

        if (createDto.getEventId() != null) {
            session.setEvent(new Event(createDto.getEventId()));
        }

        return session;
    }
}


//package com.emm.mseventoservice.mappers;
//
//import com.emm.mseventoservice.dtos.CreateSessionDTO;
//import com.emm.mseventoservice.dtos.SessionDTO;
//import com.emm.mseventoservice.models.Session;
//import com.emm.mseventoservice.models.Event;
//import org.mapstruct.Mapper;
//
//@Mapper(componentModel = "spring")
//public interface SessionMapper {
//
//    default SessionDTO toDto(Session session) {
//        if (session == null) {
//            return null;
//        }
//
//        return SessionDTO.builder()
//                .sessionId(session.getSessionId())
//                .eventId(session.getEvent() != null ? session.getEvent().getEventId() : null)
//                .title(session.getTitle())
//                .dateTime(session.getDateTime())
//                .durationMinutes(session.getDurationMinutes())
//                .speaker(session.getSpeaker())
//                .build();
//    }
//
//    default Session toEntityFromCreateDto(CreateSessionDTO createDto) {
//        if (createDto == null) {
//            return null;
//        }
//
//        Session session = Session.builder()
//                .title(createDto.getTitle())
//                .dateTime(createDto.getDateTime())
//                .durationMinutes(createDto.getDurationMinutes())
//                .speaker(createDto.getSpeaker())
//                .build();
//
//        if (createDto.getEventId() != null) {
//            Event event = new Event(createDto.getEventId());
//            session.setEvent(event);
//        }
//
//        return session;
//    }
//}