package com.kmzko.configurator.services.deployers;

import java.util.List;

public interface DetailService<T> {
     List<T> getAll();
     T getById(long id);
     T save(T t);
     T update(T t);
     boolean delete(T t);
     boolean deleteById(long id);
}
