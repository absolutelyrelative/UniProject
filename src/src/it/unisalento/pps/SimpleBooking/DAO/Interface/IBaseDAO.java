package it.unisalento.pps.SimpleBooking.DAO.Interface;

import java.util.ArrayList;

public interface IBaseDAO<T> {

    public T findById(int id);

    public ArrayList<T> findAll();

    //TODO: Deprecate T t creation / deletion methods?

    void create(T t);


    void delete(T t);

    @Deprecated
    void create(int id);

    @Deprecated
    void delete(int id);
}

