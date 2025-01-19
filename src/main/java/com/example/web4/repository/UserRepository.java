package com.example.web4.repository;

import com.example.web4.dto.UserDto;
import com.example.web4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByLogin(String login);

    User getByLogin(String login);
    User getByLoginAndPassword(String login, String password);

    User getById(Long id);
}

