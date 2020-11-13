package kz.edu.astanait.repositories.interfaces;

import javax.ws.rs.BadRequestException;

public interface CRUD <T> extends IRetrieve<T>{
    void add(T entity) throws BadRequestException;
    void update(T entity) throws BadRequestException;
    void delete(T entity) throws BadRequestException;
}
