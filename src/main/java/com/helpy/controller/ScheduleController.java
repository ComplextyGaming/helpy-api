package com.helpy.controller;

import com.helpy.dto.ScheduleRequest;
import com.helpy.dto.ScheduleResponse;
import com.helpy.exception.ResourceNotFoundException;
import com.helpy.model.Schedule;
import com.helpy.service.ExpertService;
import com.helpy.service.PlayerService;
import com.helpy.service.ScheduleService;
import com.helpy.util.ScheduleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ExpertService expertService;

    @Autowired
    private ScheduleConverter converter;

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getAll() throws Exception{
        var schedules = scheduleService.getAll();
        return new ResponseEntity<>(converter.convertScheduleToResponse(schedules), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> getById(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var schedule = scheduleService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));
        return new ResponseEntity<>(converter.convertScheduleToResponse(schedule), HttpStatus.OK);
    }

    @PostMapping("/expert/{expertId}/player/{playerId}")
    public ResponseEntity<ScheduleResponse> createSchedule(@Valid @RequestBody ScheduleRequest request,
                                                           @Valid @PathVariable(name = "expertId") Long expertId,
                                                           @Valid @PathVariable(name = "playerId") Long playerId) throws Exception{
        var expert = expertService.getById(expertId).orElseThrow(() -> new ResourceNotFoundException("Expert not found"));
        var player = playerService.getById(playerId).orElseThrow(() -> new ResourceNotFoundException("Player not found"));

        var tem = converter.convertScheduleToEntity(request);
        tem.setExpert(expert);
        tem.setPlayer(player);

        var schedule = scheduleService.create(tem);
        return new ResponseEntity<>(converter.convertScheduleToResponse(schedule), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/expert/{expertId}/player/{playerId}")
    public ResponseEntity<ScheduleResponse> updateSchedule(@Valid @RequestBody ScheduleRequest request,
                                                           @Valid @PathVariable(name = "id") Long id,
                                                           @Valid @PathVariable(name = "expertId") Long expertId,
                                                           @Valid @PathVariable(name = "playerId") Long playerId) throws Exception{
        var schedule = scheduleService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));
        var expert = expertService.getById(expertId).orElseThrow(() -> new ResourceNotFoundException("Expert not found"));
        var player = playerService.getById(playerId).orElseThrow(() -> new ResourceNotFoundException("Player not found"));

        schedule.setPlayer(player);
        schedule.setExpert(expert);
        schedule.setState(request.getState());
        schedule.setDate(request.getDate());
        schedule.setStartHour(request.getFinishHour());
        schedule.setStartMin(request.getStartMin());
        schedule.setFinishHour(request.getFinishHour());
        schedule.setFinishMin(request.getFinishMin());

        scheduleService.update(schedule);
        return new ResponseEntity<>(converter.convertScheduleToResponse(schedule), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchedule(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var schedule = scheduleService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));
        scheduleService.delete(id);
        return ResponseEntity.ok().build();
    }
}
