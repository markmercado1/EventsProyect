package upeu.mse_attendance.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import upeu.mse_attendance.dto.*;
import upeu.mse_attendance.entity.Attendance;
import upeu.mse_attendance.mapper.AttendanceMapper;
import upeu.mse_attendance.repository.AttendanceRepository;
import upeu.mse_attendance.service.AttendanceService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository repository;

    @Override
    public AttendanceResponseDTO register(AttendanceCreateDTO dto) {

        Attendance attendance = AttendanceMapper.toEntity(dto);

        // Si no viene timestamp, lo genera la entidad con @PrePersist
        if (attendance.getTimestamp() == null) {
            attendance.setTimestamp(LocalDateTime.now());
        }

        Attendance saved = repository.save(attendance);
        return AttendanceMapper.toResponse(saved);
    }

    @Override
    public List<AttendanceResponseDTO> registerGroup(AttendanceGroupCreateDTO dto) {

        List<Attendance> entities = dto.getAttendances().stream()
                .map(AttendanceMapper::toEntity)
                .peek(a -> {
                    if (a.getTimestamp() == null) {
                        a.setTimestamp(LocalDateTime.now());
                    }
                })
                .toList();

        List<Attendance> saved = repository.saveAll(entities);

        return saved.stream()
                .map(AttendanceMapper::toResponse)
                .toList();
    }

    @Override
    public AttendanceResponseDTO update(Long idAttendance, AttendanceUpdateDTO dto) {
        Attendance attendance = repository.findById(idAttendance)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        attendance.setStatus(dto.getStatus());
        attendance.setCheckInMethod(dto.getCheckInMethod());
        attendance.setObservations(dto.getObservations());

        Attendance updated = repository.save(attendance);
        return AttendanceMapper.toResponse(updated);
    }

    @Override
    public AttendanceResponseDTO getById(Long idAttendance) {
        Attendance attendance = repository.findById(idAttendance)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        return AttendanceMapper.toResponse(attendance);
    }

    @Override
    public List<AttendanceResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(AttendanceMapper::toResponse)
                .toList();
    }
}
