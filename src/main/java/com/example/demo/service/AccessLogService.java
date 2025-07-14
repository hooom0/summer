package com.example.demo.service;

import com.example.demo.entity.AccessLog;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface AccessLogService {
    Page<AccessLog> queryLogs(Long personId, String deviceId, LocalDateTime startTime, LocalDateTime endTime, int page, int size);
}
