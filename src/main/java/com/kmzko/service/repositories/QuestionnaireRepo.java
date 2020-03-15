package com.kmzko.service.repositories;

import com.kmzko.service.domains.Questionnaire;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionnaireRepo extends CrudRepository<Questionnaire, Long> {
    List<Questionnaire> findByType(String type);
    @Query(
            value = "SELECT * FROM QUESTIONNAIRE q order by q.UTIL_DATE desc limit 1",
            nativeQuery = true)
    Questionnaire findLatestRecord(String a);
}
