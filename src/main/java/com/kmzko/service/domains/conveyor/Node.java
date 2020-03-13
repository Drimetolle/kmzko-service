package com.kmzko.service.domains.conveyor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Node {
    private String name;
    private List<Detail> details;

    public Node(List<Detail> details) {
        this.details = details;
    }
}
