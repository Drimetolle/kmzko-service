package com.kmzko.configurator.controllers;

import com.kmzko.configurator.services.KmzkoService;
import com.kmzko.configurator.services.deployers.PersonalConveyorDeployer;
import org.springframework.web.bind.annotation.*;

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
