package com.kmzko.configurator.services.detailService;

import java.util.List;
import java.util.Optional;

public interface DetailService<T> {
     List<T> getAll();
     Optional<T> getById(long id);
     T save(T t);
     boolean delete(T t);
     boolean deleteById(long id);
}
