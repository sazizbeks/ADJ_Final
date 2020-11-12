package kz.edu.astanait.repositories.interfaces;

public interface CRUD <T> extends IRetrieve<T>{
    void add(T entity);
    void update(T entity);
    void delete(T entity);
}
