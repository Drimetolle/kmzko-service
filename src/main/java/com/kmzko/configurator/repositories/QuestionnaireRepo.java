package com.kmzko.configurator.repositories;

import com.kmzko.configurator.domains.conveyor.ConveyorType;
import com.kmzko.configurator.domains.questionnaire.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionnaireRepo extends JpaRepository<Questionnaire, Long> {
    List<Questionnaire> findByType(ConveyorType type);
    @Query(
            value = "SELECT * FROM QUESTIONNAIRE q order by q.created desc limit 1",
            nativeQuery = true)
    Questionnaire findLatestRecord(String a);
}
