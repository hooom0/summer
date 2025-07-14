package com.example.demo.controller;

import com.example.demo.entity.AccessLog;
import com.example.demo.service.AccessLogService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/access-logs")
public class AccessLogController {
    private final AccessLogService logService;

    public AccessLogController(AccessLogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public ResponseEntity<Page<AccessLog>> queryLogs(
            @RequestParam(required = false) Long personId,
            @RequestParam(required = false) String deviceId,
            @RequestParam(required = false) LocalDateTime startTime,
            @RequestParam(required = false) LocalDateTime endTime,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(logService.queryLogs(personId, deviceId, startTime, endTime, page, size));
    }
}