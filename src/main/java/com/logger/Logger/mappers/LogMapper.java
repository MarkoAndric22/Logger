package com.logger.Logger.mappers;

import com.logger.Logger.dtos.LogDto;
import com.logger.Logger.model.Log;
import org.springframework.stereotype.Component;

@Component
public class LogMapper implements GenericMapper<Log, LogDto> {

    public Log toEntity(LogDto dto) {
        return new Log(dto.getMessage(), dto.getLogType(), dto.getCreatedDate());
    }

    public LogDto toDto(Log entity) {
        return new LogDto(entity.getMessage(), entity.getLogType(),entity.getCreatedDate());
    }
}
