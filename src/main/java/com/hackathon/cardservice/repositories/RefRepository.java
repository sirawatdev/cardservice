package com.hackathon.cardservice.repositories;

import com.hackathon.cardservice.model.Refs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RefRepository extends JpaRepository<Refs,Long> {

    @Query("FROM Refs u where u.cardId = :cardId and u.refId = :refId")
    Refs findAllByCardIdAndRefId(@Param("cardId") Long cardId,@Param("refId") Long refId);
}
