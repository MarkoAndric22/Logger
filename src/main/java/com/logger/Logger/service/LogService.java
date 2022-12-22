package com.logger.Logger.service;

import com.logger.Logger.dtos.LogDto;
import com.logger.Logger.exceptions.NotFoundException;
import com.logger.Logger.exceptions.TooLongException;
import com.logger.Logger.exceptions.UnauthorizedUserException;
import com.logger.Logger.mappers.LogMapper;
import com.logger.Logger.model.Log;
import com.logger.Logger.model.LogEnum;
import com.logger.Logger.model.User;
import com.logger.Logger.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private LogMapper logMapper;

    public void createLog(Log log) throws TooLongException, NotFoundException, UnauthorizedUserException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        for (var e:LogEnum.values()){
            if (e.ordinal()==log.getLogType()){
                log.setMessage(e.name()+" - "+log.getMessage());
            }else{
                log.setMessage(log.getMessage());
            }
        }
        if(auth == null || auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            throw new UnauthorizedUserException("401 - Unauthorized");
        }
        else if (log.getMessage().length()>1024){
            throw new TooLongException("413 - Payload too large\nMessage should be less than 1024");
        }

       else if (log.getLogType() < 0 || log.getLogType() > 7){
            throw new NotFoundException("400 - Bad Request\nIncorrect logType");
        }


       logRepository.save(new Log(log.getMessage(),log.getLogType(),log.getCreatedDate(),auth.getName()));
    }

    public List<LogDto> searchByParam(Date dateFrom, Date dateTo, String message, Integer logType) throws UnauthorizedUserException, NotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth == null || auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            throw new UnauthorizedUserException("401 - Unauthorized");
        }


            if (!Objects.isNull(dateTo) && !Objects.isNull(dateFrom)) {
                if (dateFrom.after(dateTo) || dateTo.before(dateFrom)) {
                    throw new NotFoundException("400 - Bad Request\nInvalid dates");
                }
            }

            if (logType != null && logType>7){
                throw new NotFoundException("400 - Bad Request\nInvalid log type");
            }
        return logRepository.findByParam(dateFrom, dateTo, message, logType).stream().map(logMapper::toDto).collect(Collectors.toList());

    }

}
