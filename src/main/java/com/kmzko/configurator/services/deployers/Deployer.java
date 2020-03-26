package com.kmzko.configurator.services.deployers;

public interface Deployer<T> {
     T save(T o);
     boolean delete(T o);
     boolean deleteById(long o);
}
