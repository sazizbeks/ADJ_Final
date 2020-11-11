package kz.edu.astanait.repositories.interfaces;

import java.util.List;

public interface CRUD <T> {
    T queryOne(String id);
    List<T> getAll();
    void add(T entity);
    void update(T entity);
    void delete(T entity);
}
