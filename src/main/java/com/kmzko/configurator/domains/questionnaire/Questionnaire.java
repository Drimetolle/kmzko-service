package com.kmzko.configurator.domains.questionnaire;

import com.kmzko.configurator.domains.AbstractConveyor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questionnaire")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Questionnaire extends AbstractConveyor {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "questionnaire_id", nullable=false)
    private List<Rate> rateList;
}
