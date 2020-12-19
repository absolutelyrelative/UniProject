package it.unisalento.pps.SimpleBooking.DAO.Interface;

import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Utente;

import java.util.ArrayList;
import java.util.Date;

public interface IBeniDAO extends IBaseDAO<Beni> {
    public ArrayList<Beni> sortByDate(Date Inizio, Date Fine);

    public ArrayList<Beni> sortByCost(Float cost, int type); //Type 0 = per day, type 1 = per week, type 2 = per month

    public ArrayList<Beni> sortByCreator(Utente u); //Used to create Mini Catalogue for Venditore

    void publishBene(Beni b);

    void updateBene(Beni b); //TODO: Works with 'stato bene', is necessary to mark a bene as ordered, among other things. Break into smaller parts maybe?
}
