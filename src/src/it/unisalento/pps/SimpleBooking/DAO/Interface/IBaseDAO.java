package it.unisalento.pps.SimpleBooking.DAO.Interface;

import java.util.ArrayList;

public interface IBaseDAO<T> {

    public T findById(int id);

    public ArrayList<T> findAll();

    //TODO: Deprecate T t creation / deletion methods?
    @Deprecated
    void create(T t);

    @Deprecated
    void delete(T t);

    void create(int id);

    void delete(int id);
}

