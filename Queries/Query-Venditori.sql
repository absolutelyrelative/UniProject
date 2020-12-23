SELECT U.idUtente, U.email, U.password, U.username, Ven.idVenditore, Ven.Utente_idUtente FROM Utente as U
INNER JOIN Venditore as Ven
ON Ven.Utente_idUtente = U.IdUtente
WHERE Ven.idVenditore = XX
LIMIT 1;