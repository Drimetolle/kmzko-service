package com.kmzko.service.domains;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questionnaire")
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
//    private List<Rate> rateList;
//
//    public Questionnaire() {
//        this.rateList = new ArrayList<>();
//    }
//
//    public Questionnaire(List<Rate> rateList) {
//        this.rateList = rateList;
//    }
//
//    public void pushRate(Rate rate) {
//        rateList.add(rate);
//    }
}
