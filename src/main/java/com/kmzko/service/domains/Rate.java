package com.kmzko.service.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rate")
@Getter
@Setter
@NoArgsConstructor
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String value;

    public Rate(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "rate_id", nullable = false)
    @JsonIgnore
    private Questionnaire questionnaire;
}
