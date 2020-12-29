package it.unisalento.pps.SimpleBooking.DAO.Interface;

import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Immagine;

import java.util.ArrayList;

public interface IImmagineDAO extends IBaseDAO<Immagine> {
    ArrayList<Immagine> getImagesFromBene(Beni b);
}
