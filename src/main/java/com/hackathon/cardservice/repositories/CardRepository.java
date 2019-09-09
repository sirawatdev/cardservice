package com.hackathon.cardservice.repositories;

import com.hackathon.cardservice.model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Cards,Long> {

    Cards findAllById(Long id);

    @Query("FROM Cards u where u.userId = :userId")
    List<Cards> findAllByUserId(@Param("userId") Long userId);

    @Query("FROM Cards u where u.digits = :digits")
    Cards findAllByDigits(@Param("digits") Long digits);

}
