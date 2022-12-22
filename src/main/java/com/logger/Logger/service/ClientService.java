package com.logger.Logger.service;

import com.logger.Logger.dtos.UserDto;
import com.logger.Logger.dtos.UserRegisterDto;
import com.logger.Logger.exceptions.EntityExistsException;
import com.logger.Logger.exceptions.InvalidParamException;
import com.logger.Logger.exceptions.NoContentException;
import com.logger.Logger.exceptions.UnauthorizedUserException;
import com.logger.Logger.mappers.RegisterMapper;
import com.logger.Logger.mappers.UserMapper;
import com.logger.Logger.model.Log;
import com.logger.Logger.model.User;
import com.logger.Logger.repository.ClientRepository;
import com.logger.Logger.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private RegisterMapper registerMapper;

    @Autowired
    private UserMapper userMapper;

    public boolean isValidEmail(String email)
    {
        String emailRegex = "^(.+)@(.+)$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public boolean isValidPassword(String password)
    {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$";

        Pattern pat = Pattern.compile(passwordRegex);
        if (password == null || password.isEmpty() || password.isBlank() || password.length()<8)
            return false;
        return pat.matcher(password).matches();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clientRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user " + username + " does not exist!"));
    }

    public void register(UserRegisterDto user) throws EntityExistsException, InvalidParamException  {
        Optional<User> existingUser = clientRepository.findById(user.getUsername());
        Optional<User> existingUser2 = clientRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()){
            if ((existingUser.get().getUsername().equals(user.getUsername()))) {
                throw new EntityExistsException("409 - Conflict\n\tUsername already exists!");
            }
        }
        else if (existingUser2.isPresent()){
            if ((existingUser2.get().getEmail().equals(user.getEmail()))){
                throw new EntityExistsException("409 - Conflict\n\tEmail already exists!");
            }
        }
        else if (!isValidEmail(user.getEmail())) {
            throw new InvalidParamException ("400 - Bad Request \\n\\tEmail must be valid.");
        }
        else if ((user.getUsername().length()<3)){
            throw new InvalidParamException ("400 - Bad Request \\n\\tUsername at least 3 characters");
        }
        else if (!isValidPassword(user.getPassword())){
            throw new InvalidParamException ("400 - Bad Request \\n\\tPassword at least 8 characters and one letter and one number");
        }

        registerMapper.toDto(clientRepository.save(registerMapper.toEntity(user)));

    }

    public List<UserDto> clients() throws UnauthorizedUserException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_CLIENT"))) {
            throw new UnauthorizedUserException("401 - Unauthorized\nCorrect user, but not admin");
        }

        List<User> users = clientRepository.findClients();
        List<UserDto> usersDto = new ArrayList<>();
        for (User user: users){
            List<Log> logs = logRepository.findByClientUsername(user.getUsername());

            int logCount = logs.size();
            UserDto userDto = new UserDto(user.getUsername(),user.getEmail(),logCount);
            usersDto.add(userDto);
        }
        return usersDto;
    }

    public void changePassword(String username, String password) throws InvalidParamException, NoContentException, UnauthorizedUserException {
        User user = clientRepository.findByUsername(username).get();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (password==null || password.isEmpty() || password.isBlank()) {
            throw new NoContentException("204 - No content");
        }
         else if (!isValidPassword(password)){
             throw new InvalidParamException("403 - Forbidden\nIncorrect password");
        }
        else if(auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_CLIENT"))) {
            throw new UnauthorizedUserException("401 - Unauthorized\nCorrect user, but not admin");
        }
        user.setPassword(password);
        clientRepository.save(user);
    }

}
