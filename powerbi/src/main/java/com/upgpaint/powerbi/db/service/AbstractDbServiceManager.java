package com.upgpaint.powerbi.db.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public interface AbstractDbServiceManager<T, ID extends Serializable> {

    // Create one entity in DB.
    public T save(T entity);

    // Create multiple entities in DB.
    public Iterable<T> saveAll(List<T> entities);

    // Update one entity in DB
    public T update(T entity);

    // Update entities in DB
    public Iterable<T> update(List<T> entities);

    // Load one entity from DB by id.
    public Optional<T> loadOne(ID id);

    // Load multiple entities from DB by ids.
    public Iterable<T> loadAll(List<ID> ids);

    // Delete one entity from DB by Id
    public void deleteById(ID id);

    // Delete one entity from DB.
    public void delete(T entity);
    
 
    // Delete multiple entities from DB.
    public void deleteList(Iterable<T> entities);

    // Delete all
    public void deleteAll();

    // Loads all the entities from DB by iterator
    Iterable<T> getAll();

    // Get total number of rows from DB
    Long getCount();

}
