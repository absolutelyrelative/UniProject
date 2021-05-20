Repository for the Software Engineering course 2020/2021.
The project guidelines were established by the professor and they consisted in utilising Scrum, DAO / SingleTon patterns, and Java.
I went beyond the expectations and studied the ICONIX process (As you can see in the documentation given), alongside some other programming patterns visible in the project code.

In the Diagrams folder you can find all the documentation related to the Scrum development practices employed.
In the Queries folder you can find some dummy SQL queries written during the implementation of the DAO pattern.
 Unfortunately, I was constrained to use "raw" MySQL and therefore had to inject some SQL upon Schema creation, and it is what is present at the bottom of the page.
 I also went beyond what was required of me in this instance and attempted to use industry standard and not store passwords in plaintext, obviously.
The client code can be found in the src folder. I went beyond the requirements and implemented Gradle, I studied Swing and edited some of its objects to fit my needs, I  implemented more programming patterns, such as a 'Result' object, and I tried to unify all the GUI and Event Listeners in some-sort of "isomorphism".
I had to "hack" a fix because Swing did not provide me with a straightforward way to implement multiple-picture scrolling, by dynamically creating Object Lists.

If I had to change something it would be a more consistent way to name variables, perhaps a more elegant solution to the UI implementation using other libraries, and learning NoSQL / MongoDB instead of MySQL for this type of project.
All in all I think the project shows I am able to use Agile development practices, as well as the other aforementioned programming patterns, and I am capable, willing, and consistently going beyond the minimum acceptable to provide more elegant solutions and, in general, constantly study and improve.

The following text can be ignored as it is an instruction / suggestion for my professor.



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
