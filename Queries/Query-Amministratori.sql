--FIND BY ID AND PARSE AMMINISTRATORI
SELECT U.idUtente, U.email, U.password, U.username, Adm.idAmministratore, Adm.Utente_idUtente FROM Utente as U
INNER JOIN Amministratore as Adm
ON Adm.Utente_idUtente = U.IdUtente
WHERE Adm.idAmministratore = 1;

--PARSE ALL AMMINISTRATORI
SELECT U.idUtente, U.email, U.password, U.username, Adm.idAmministratore, Adm.Utente_idUtente FROM Utente as U
INNER JOIN Amministratore as Adm
ON Adm.Utente_idUtente = U.IdUtente;
