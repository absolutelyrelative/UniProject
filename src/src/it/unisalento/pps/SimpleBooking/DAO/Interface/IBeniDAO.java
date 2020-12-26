package it.unisalento.pps.SimpleBooking.DAO.Interface;

import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.Model.Venditore;

import java.util.ArrayList;
import java.util.Date;

//TODO: IMPLEMENT SORT BY LOCATION

public interface IBeniDAO extends IBaseDAO<Beni> {
    public ArrayList<Beni> sortByDate(java.sql.Date Inizio, java.sql.Date Fine);

    public ArrayList<Beni> sortByCost(Float cost, int type); //Type 0 = per day, type 1 = per week, type 2 = per month

    public ArrayList<Beni> sortByCreator(Venditore v); //Used to create Mini Catalogue for Venditore

    void publishBene(Beni b);

    void updateBene(Beni b_old, Beni b_new); //TODO: Works with 'stato bene', is necessary to mark a bene as ordered, among other things. Break into smaller parts maybe?
}
