package kz.edu.astanait.repositories.interfaces;

import javax.ws.rs.BadRequestException;

// Create, Retrieve, Delete
public interface CRD<T> extends IRetrieve<T> {
    void add(T entity) throws BadRequestException;
    void delete(T entity) throws BadRequestException;
}
