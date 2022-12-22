package com.logger.Logger.controller;

import com.logger.Logger.dtos.LogDto;
import com.logger.Logger.exceptions.NotFoundException;
import com.logger.Logger.exceptions.TooLongException;
import com.logger.Logger.exceptions.UnauthorizedUserException;
import com.logger.Logger.model.Log;
import com.logger.Logger.model.LogEnum;
import com.logger.Logger.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/logs")
public class LogController {

    @Autowired
    private LogService logService;

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    @PostMapping("/create")
    public ResponseEntity<Object> createLog(@RequestBody Log log){
        try {
            logService.createLog(log);
            return ResponseEntity.status(HttpStatus.OK).body("200 - OK");
        } catch (UnauthorizedUserException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }catch (TooLongException e) {
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(e.getMessage());
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchByParam(@RequestParam(required = false) @DateTimeFormat(pattern = DATE_PATTERN) Date dateFrom,
                                                @RequestParam(required = false) @DateTimeFormat(pattern = DATE_PATTERN) Date dateTo,
                                                @RequestParam(required = false) String message,
                                                @RequestParam(required = false) Integer logType){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(logService.searchByParam(dateFrom,dateTo,message,logType));
        } catch (UnauthorizedUserException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
