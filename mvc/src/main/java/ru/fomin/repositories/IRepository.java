package ru.fomin.repositories;

import java.util.List;

public interface IRepository<T, ID> {

    List<T> findAll();

    T findById(ID id);

    ID save(T model);

}
