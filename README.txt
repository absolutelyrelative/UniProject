README:
Consiglio l'utilizzo di dbSchema.sql per generare il database, in quanto contiene la creazione del primo utente Admin.
Le query utilizzate sono comunque queste:

INSERT INTO Utente(email, password, username)
VALUES('simplebooking.paolo@gmail.com','1721920','SBT');
INSERT INTO Amministratore(Utente_idUtente)
VALUES('1');
INSERT INTO Compratore(Utente_idUtente)
VALUES('1');
INSERT INTO Venditore(Utente_idUtente)
VALUES('1');

NB: La password '1721920' è un HASH della vera password, che è 8668.