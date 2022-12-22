package com.logger.Logger.repository;

import com.logger.Logger.dtos.UserDto;
import com.logger.Logger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClientRepository extends JpaRepository<User,String> {

    @Query(value = "select * from users where user_role like 'ROLE_CLIENT'",nativeQuery = true)
    List<User> findClients();
    Optional<User> findByEmail(String email);
    Optional<User>findByUsername(String username);

}
