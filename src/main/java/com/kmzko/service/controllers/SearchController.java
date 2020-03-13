package com.kmzko.service.controllers;

import com.kmzko.service.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private SearchService service;

    @GetMapping(produces = "application/json")
    public ResponseEntity<String> getListOfConveyorType(@RequestBody Map<String, String> body) {
        service.getNearConveyors(body);
        return ResponseEntity.ok("");
    }
}
