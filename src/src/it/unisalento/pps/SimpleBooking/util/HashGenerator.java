package it.unisalento.pps.SimpleBooking.util;

public class HashGenerator {
    //Per le password, ho deciso di fare un pochino di più e, come credo sia la norma, salvare le password nel DB
    //non in plain-text. Per non spendere troppo tempo, già limitato per me, ho creato un semplice hash usando
    //librerie standard. La password sarà salvata in DB con il suo hash, e verificata con il suo hash
    //(Se due String a & b, sono tali che a.equals(b), allora i loro hash saranno uguali.
    private int hash;

    public HashGenerator() {
    }

    public int returnHash(String password) {
        hash = password.hashCode();
        return hash;
    }
}
