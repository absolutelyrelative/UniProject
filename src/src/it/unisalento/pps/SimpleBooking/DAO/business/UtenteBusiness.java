package it.unisalento.pps.SimpleBooking.DAO.business;

public class UtenteBusiness {

    private static UtenteBusiness instance;

    public static synchronized UtenteBusiness getInstance() {
        if(instance == null)
            instance = new UtenteBusiness();
        return instance;
    }

    private UtenteBusiness(){}


}
