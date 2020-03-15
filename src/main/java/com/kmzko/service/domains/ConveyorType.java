package com.kmzko.service.domains;

public enum ConveyorType {
    TAPE ("tape");

    private String view;

    public String getValue() {
        return view;
    }

    ConveyorType(String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }

    public static ConveyorType saveValueOf(String value) {
        for(ConveyorType v : values())
            if(v.toString().equalsIgnoreCase(value)) return v;
        return null;
    }
}
