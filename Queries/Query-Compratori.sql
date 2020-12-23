SELECT U.idUtente, U.email, U.password, U.username, Com.idCompratore, Com.Utente_idUtente FROM Utente as U
INNER JOIN Compratore as Com
ON Com.Utente_idUtente = U.IdUtente
WHERE Com.idCompratore = XX;


INSERT INTO Compratore(Utente_idUtente) VALUES(XX)