package it.unisalento.pps.SimpleBooking.DAO.Interface;

import it.unisalento.pps.SimpleBooking.util.Result;

import java.util.ArrayList;

public interface IBaseDAO<T> {

    T findById(int id);

    ArrayList<T> findAll();

    //TODO: Deprecate T t creation / deletion methods?

    //TODO: ADD RESULT RETURN!!
    Result create(T t);


    Result delete(T t);
}

