Repository for the Software Engineering course 2020/2021.
The project guidelines were established by the professor and they consisted in utilising Scrum, DAO / SingleTon patterns, and Java.
I went beyond the expectations and studied the ICONIX process (As you can see in the documentation given), alongside some other programming patterns visible in the project code.

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
