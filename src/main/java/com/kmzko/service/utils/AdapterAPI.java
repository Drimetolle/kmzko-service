package com.kmzko.service.utils;

import com.kmzko.service.domains.conveyor.Conveyor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdapterAPI {
    List<Conveyor> getNearConveyors();
}
