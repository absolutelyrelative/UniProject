package it.unisalento.pps.SimpleBooking.DAO.Interface;

import it.unisalento.pps.SimpleBooking.Model.Amministratore;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Venditore;

import java.util.ArrayList;

//TODO: IMPLEMENT SORT BY LOCATION
//TODO: IMPLEMENT STATO BENE
//TODO: CREATE() METHOD MUST NOT CONTAIN PRIMARY KEY? CORRECT ALL! - probable workaround is to set id to null, TEST:
//Insert into utente(idUtente, email, password, username) VALUES(NULL,'nullemail','nullpassword','nullusername') works
//Insert into utente(idUtente, email, password, username) VALUES('','nullemail','nullpassword','nullusername') doesn't work
//TODO: IS public float getCumulativeCost(Line_Item LI) NECESSARY?
//TODO: IMPLEMENT METHOD TO FIND IF USER IS ADMIN/VEND/COMPR?
//TODO: TEST CASCADE DELETE OF TIPO_BENE -> BENE

public interface IBeniDAO extends IBaseDAO<Beni> {
    public ArrayList<Beni> sortByDate(java.sql.Date Inizio, java.sql.Date Fine);

    public ArrayList<Beni> sortByCost(Float cost, int type); //Type 0 = per day, type 1 = per week, type 2 = per month

    public ArrayList<Beni> sortByCreator(Venditore v); //Used to create Mini Catalogue for Venditore

    public ArrayList<Beni> sortByApprover(Amministratore a);

    void publishBene(Beni b); //0 = unpublished, 1 = published

    void unpublishBene(Beni b); //0 = unpublished, 1 = published

    void updateBene(Beni b_old, Beni b_new); //TODO: Works with 'stato bene', is necessary to mark a bene as ordered, among other things. Break into smaller parts maybe?
}
