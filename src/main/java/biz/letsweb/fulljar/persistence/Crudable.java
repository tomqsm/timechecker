package biz.letsweb.fulljar.persistence;

import java.util.List;

/**
 *
 * @author Tomasz
 */
public interface Crudable <T>{
    void create(final T t);
    T find(final int id);
    List<T> findAll();
    T findLast();
    void update(final T t);
    void delete(final T t);
    void delete(final int id);
}
