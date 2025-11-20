package upeu.mse_attendance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import upeu.mse_attendance.dto.*;
import upeu.mse_attendance.service.AttendanceService;

import java.util.List;

@RestController
@RequestMapping("/attendances")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService service;

    @PostMapping
    public AttendanceResponseDTO register(@RequestBody AttendanceCreateDTO dto) {
        return service.register(dto);
    }

    @PostMapping("/group")
    public List<AttendanceResponseDTO> registerGroup(@RequestBody AttendanceGroupCreateDTO dto) {
        return service.registerGroup(dto);
    }

    @PutMapping("/{id}")
    public AttendanceResponseDTO update(
            @PathVariable Long id,
            @RequestBody AttendanceUpdateDTO dto
    ) {
        return service.update(id, dto);
    }

    @GetMapping("/{id}")
    public AttendanceResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<AttendanceResponseDTO> getAll() {
        return service.getAll();
    }
}
