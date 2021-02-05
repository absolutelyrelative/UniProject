package it.unisalento.pps.SimpleBooking.DAO.Interface;

import java.util.ArrayList;

public interface IBaseDAO<T> {

    T findById(int id);

    ArrayList<T> findAll();

    //TODO: Deprecate T t creation / deletion methods?

    //TODO: ADD RESULT RETURN!!
    void create(T t);


    void delete(T t);

    @Deprecated
    void create(int id);

    @Deprecated
    void delete(int id);
}

