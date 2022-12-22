package com.logger.Logger.repository;

import com.logger.Logger.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log,Integer> {

    @Query(value = "select * from logs " +
            "where (created_date between :dateFrom and :dateTo)  or message like LOWER('%' || :message || '%') or log_type = :logType", nativeQuery = true)
    List<Log> findByParam(@Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo, @Param("message") String message, @Param("logType") Integer logType);

    @Query(value = "", nativeQuery = true)
    List<Log> findByClientUsername(@Param("username") String username);
}