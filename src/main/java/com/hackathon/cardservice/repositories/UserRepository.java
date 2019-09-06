package com.hackathon.cardservice.repositories;

import com.hackathon.cardservice.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<Users,String> {

    @Query("FROM Users u where (u.userName = :username)")
    List<Users> getUserFromUsername(@Param("username") String username);

}
