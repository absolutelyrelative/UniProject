package it.unisalento.pps.SimpleBooking.DAO.Interface;

import java.util.ArrayList;

public interface IBaseDAO<T> {

    public T findById(int id);
    public ArrayList<T> findAll();

}

