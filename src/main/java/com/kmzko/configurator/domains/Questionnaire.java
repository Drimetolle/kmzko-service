package com.kmzko.configurator.domains;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "questionnaire")
@Getter
@Setter
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ConveyorType type;
    @CreationTimestamp
    private Date utilDate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rate> rateList;

    public Questionnaire() {
        this.rateList = new ArrayList<>();
    }

    public Questionnaire(List<Rate> rateList, ConveyorType type) {
        this.rateList = rateList;
        this.type = type;
    }
}
