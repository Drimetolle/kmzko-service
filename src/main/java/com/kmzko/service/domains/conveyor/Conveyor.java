package com.kmzko.service.domains.conveyor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Conveyor {
    private String name;
    private List<Node> nodes;

    public Conveyor(List<Node> nodes) {
        this.nodes = nodes;
    }
}
