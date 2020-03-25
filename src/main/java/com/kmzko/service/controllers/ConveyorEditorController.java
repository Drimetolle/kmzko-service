package com.kmzko.service.controllers;

import com.kmzko.service.domains.ConveyorType;
import com.kmzko.service.domains.conveyor.Conveyor;
import com.kmzko.service.domains.conveyor.Detail;
import com.kmzko.service.services.KmzkoService;
import com.kmzko.service.services.deployers.PersonalConveyorDeployer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/editor")
@CrossOrigin(origins = "*")
public class ConveyorEditorController {
    private final KmzkoService service;
    private final PersonalConveyorDeployer deployer;

    public ConveyorEditorController(KmzkoService service, PersonalConveyorDeployer deployer) {
        this.service = service;
        this.deployer = deployer;
    }


}
