package it.unisalento.pps.SimpleBooking.DAO.Interface;

import it.unisalento.pps.SimpleBooking.Model.Pagamento;

public interface IPagamentoDAO extends IBaseDAO<Pagamento> {
    void updatePaymentStato(Pagamento p, int status); //0 = Not Paid, 1 = Paid
}
