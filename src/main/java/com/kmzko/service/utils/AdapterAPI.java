package com.kmzko.service.utils;

import com.kmzko.service.domains.Rate;
import com.kmzko.service.domains.conveyor.Conveyor;
import com.kmzko.service.domains.conveyor.Detail;

import java.util.List;
import java.util.Set;

public interface AdapterAPI {
    List<Conveyor> getNearConveyors(List<Rate> rates);
    Conveyor getConveyorById(long id);
    Detail getDetailById(long id);
//    Object getCompatibilityDetails(List<Detail> details, Detail detail);
//    Set<Long> getAllIdConveyor();
//    List<Conveyor> getConveyorByRangeId(Set<Long> range);
}
