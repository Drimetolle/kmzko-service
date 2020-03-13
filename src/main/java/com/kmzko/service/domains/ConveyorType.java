package com.kmzko.service.domains;

public enum ConveyorType {
    TAPE ("Ленточный");

    private String view;

    ConveyorType(String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }
}
