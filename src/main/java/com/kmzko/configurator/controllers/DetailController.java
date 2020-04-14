package com.kmzko.configurator.controllers;

import com.kmzko.configurator.dto.DetailDto;
import com.kmzko.configurator.mappers.DetailMapper;
import com.kmzko.configurator.services.detailService.ComponentDetailService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/details")
public class DetailController {
    private final ComponentDetailService service;
    private final DetailMapper mapper;

    public DetailController(ComponentDetailService service, DetailMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DetailDto>> getAllDetails() {
        return ResponseEntity.ok(service.getAll().stream().map(mapper::toDto).collect(Collectors.toList()));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DetailDto> deployNewDetail(@Valid @RequestBody DetailDto detail) {
        return ResponseEntity.ok(mapper.toDto(service.save(mapper.toEntity(detail))));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DetailDto> deployNewDetail(@PathVariable Long id) {
        if (!service.deleteById(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }
}
