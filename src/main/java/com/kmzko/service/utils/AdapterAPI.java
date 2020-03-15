package com.kmzko.service.utils;

import com.kmzko.service.domains.conveyor.Conveyor;

import java.util.List;

public interface AdapterAPI {
    List<Conveyor> getNearConveyors();
}
