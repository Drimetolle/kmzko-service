package com.kmzko.configurator.repositories;

import com.kmzko.configurator.entity.user.PersonalQuestionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalQuestionnaireRepo extends JpaRepository<PersonalQuestionnaire, Long> {
}
