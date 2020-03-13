package com.kmzko.service.repositories;

import com.kmzko.service.domains.Questionnaire;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface QuestionnaireRepo extends CrudRepository<Questionnaire, Long> {
    List<Questionnaire> findByType(String type);
}
