package com.kmzko.service.services.kmzko.api;

import com.kmzko.service.domains.ConveyorType;
import com.kmzko.service.domains.Rate;
import com.kmzko.service.domains.conveyor.Conveyor;
import com.kmzko.service.domains.conveyor.Detail;

import java.util.List;

public interface AdapterAPI {
    List<Conveyor> getNearConveyors(List<Rate> rates);
    Conveyor getConveyorById(long id);
    Detail getDetailById(long id);
    List<Detail> getOptionsByType(ConveyorType type);
    Object getCompatibilityDetails(List<Detail> details, Detail detail);
}
