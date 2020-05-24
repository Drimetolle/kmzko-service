package com.kmzko.configurator.repositories;

import com.kmzko.configurator.entity.user.questionnaire.PersonalQuestionnaire;
import com.kmzko.configurator.entity.user.questionnaire.TestQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalQuestionnaire1Repo extends JpaRepository<TestQ, Long> {
}
