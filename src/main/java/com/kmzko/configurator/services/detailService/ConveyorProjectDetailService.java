package com.kmzko.configurator.services.detailService;

import com.kmzko.configurator.entity.user.ConveyorProject;
import com.kmzko.configurator.repositories.ConveyorProjectRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConveyorProjectDetailService implements DetailService<ConveyorProject> {
    private final ConveyorProjectRepo projectRepo;

    public ConveyorProjectDetailService(ConveyorProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    @Override
    public List<ConveyorProject> getAll() {
        return projectRepo.findAll();
    }

    @Override
    public Optional<ConveyorProject> getById(long id) {
        return projectRepo.findById(id);
    }

    @Override
    public ConveyorProject save(ConveyorProject conveyorProject) {
        return projectRepo.save(conveyorProject);
    }

    @Override
    public ConveyorProject update(ConveyorProject conveyorProject) {
        return null;
    }

    @Override
    public boolean delete(ConveyorProject conveyorProject) {
        try {
            projectRepo.delete(conveyorProject);
        }
        catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteById(long id) {
        try {
            projectRepo.deleteById(id);
        }
        catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }
}
