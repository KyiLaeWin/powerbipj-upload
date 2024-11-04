package com.upgpaint.powerbi.db.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.upgpaint.powerbi.db.service.AbstractDbServiceManager;

@NoRepositoryBean
public class AbstractDbServiceManagerImpl<T, ID extends Serializable> implements AbstractDbServiceManager<T, ID> {

    private JpaRepository<T, ID> jpaRepository;

    @Autowired
    public AbstractDbServiceManagerImpl(JpaRepository<T, ID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public T save(T entity) {
        return this.jpaRepository.save(entity);
    }

    @Override
    public Iterable<T> saveAll(List<T> entities) {
        return this.jpaRepository.saveAll(entities);
    }

    @Override
    public T update(T entity) {
        return this.jpaRepository.save(entity);
    }

    @Override
    public Iterable<T> update(List<T> entities) {
        Iterable<T> entitiesIterable = entities;
        return this.jpaRepository.saveAll(entitiesIterable);
    }

    @Override
    public Optional<T> loadOne(ID id) {
        return this.jpaRepository.findById(id);
    }

    @Override
    public Iterable<T> loadAll(List<ID> ids) {
        Iterable<ID> idsIterable = ids;
        return this.jpaRepository.findAllById(idsIterable);
    }

    @Override
    public void deleteById(ID id) {
        this.jpaRepository.deleteById(id);
    }

    @Override
    public void delete(T entity) {
        this.jpaRepository.delete(entity);
    }

    @Override
    public void deleteList(Iterable<T> entities) {
        this.jpaRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        this.jpaRepository.deleteAll();
    }

    @Override
    public Iterable<T> getAll() {
        return this.jpaRepository.findAll();
    }

    @Override
    public Long getCount() {
        return this.jpaRepository.count();
    }

}
