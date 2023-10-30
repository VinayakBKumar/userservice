package com.dev.userservice.repositories;

import com.dev.userservice.dtos.UserDto;
import com.dev.userservice.models.User;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT COUNT(u.email) FROM User u where u.email = :email")
    int getUserCountByEmail(String email);

    Optional<User> findByEmail(String email);

}
