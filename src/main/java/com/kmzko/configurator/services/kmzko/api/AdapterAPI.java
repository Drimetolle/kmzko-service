package com.kmzko.configurator.services.kmzko.api;

import com.kmzko.configurator.entity.user.conveyor.OptionalDetail;
import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.domains.questionnaire.Rate;
import com.kmzko.configurator.domains.conveyor.Conveyor;
import com.kmzko.configurator.domains.conveyor.Detail;

import java.util.List;

public interface AdapterAPI {
    List<Conveyor> getNearConveyors(List<Rate> rates);
    Conveyor getConveyorById(long id);
    Detail getDetailById(long id);
    List<OptionalDetail> getOptionsByType(ConveyorType type);
    Object getCompatibilityDetails(List<Detail> details, Detail detail);
}
