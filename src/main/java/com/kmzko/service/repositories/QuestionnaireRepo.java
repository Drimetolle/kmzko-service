package com.kmzko.service.repositories;

import com.kmzko.service.domains.Questionnaire;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionnaireRepo extends CrudRepository<Questionnaire, Long> {

}
