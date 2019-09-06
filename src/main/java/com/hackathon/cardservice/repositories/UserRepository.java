package com.hackathon.cardservice.repositories;

import com.hackathon.cardservice.model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository {
    @Query("FROM Appointments u where (u.userName = :username)")
    List<Users> getUserFromUsername(@Param("username") String username);

}
