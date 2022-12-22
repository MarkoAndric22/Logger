package com.logger.Logger.controller;

import com.logger.Logger.dtos.UserDto;
import com.logger.Logger.dtos.UserRegisterDto;
import com.logger.Logger.exceptions.EntityExistsException;
import com.logger.Logger.exceptions.InvalidParamException;
import com.logger.Logger.exceptions.NoContentException;
import com.logger.Logger.exceptions.UnauthorizedUserException;
import com.logger.Logger.model.User;

import com.logger.Logger.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/clients")
public class ClientController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ClientService clientService;


    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Object> loginUser(User client){
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    client.getUsername(), client.getPassword()));
           // User userDetails = (User) authentication.getPrincipal();
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.status(HttpStatus.OK).body("200 - OK");
        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("400 - Bad Request \n\tEmail/Username or password incorrect");
        }
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserRegisterDto user){
        try {
            clientService.register(user);
            return ResponseEntity.status(HttpStatus.OK).body("201 - Registered");
        }catch (EntityExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (InvalidParamException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    }

    @GetMapping
    public ResponseEntity<?> clients(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clientService.clients());
        } catch (UnauthorizedUserException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PatchMapping(path = "/{clientId}/reset-password")
    public ResponseEntity<String> changePassword(@PathVariable String clientId, @RequestBody(required = false) String password){
        try {
            clientService.changePassword(clientId, password);
            return ResponseEntity.status(HttpStatus.OK).body("200 - OK");
        }catch (NoContentException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }catch (UnauthorizedUserException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }catch (InvalidParamException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
