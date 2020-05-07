package com.kmzko.configurator.repositories;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.domains.questionnaire.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionnaireRepo extends JpaRepository<Questionnaire, Long> {
    List<Questionnaire> findByType(ConveyorType type);
    @Query(
            value = "SELECT * FROM QUESTIONNAIRE q WHERE q.TYPE like :conveyortype order by q.created desc limit 1",
            nativeQuery = true)
    Optional<Questionnaire> findLatestRecord(@Param("conveyortype") String type);
}
