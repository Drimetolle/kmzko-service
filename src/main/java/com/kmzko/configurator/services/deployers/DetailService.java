package com.kmzko.configurator.services.deployers;

import java.util.List;
import java.util.Optional;

public interface DetailService<T> {
     List<T> getAll();
     Optional<T> getById(long id);
     T save(T t);
     T update(T t);
     boolean delete(T t);
     boolean deleteById(long id);
}
