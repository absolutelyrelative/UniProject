package it.unisalento.pps.SimpleBooking.dbInterface;

/*
 * Classe dedicata alla gestione del Database.
 * Gestisce l'apertura e la chiusura della connessione col Database
 * Fornisce i metodi per l'esecuzione delle query sul Database
 */

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;

public class DbConnection {

    private static Connection db;       // La connessione col Database
    private static boolean connesso;    // Flag che indica se la connessione � attiva o meno
    private static DbConnection instance; //istanza statica della classe

    public static DbConnection getInstance() {
        if (instance == null)
            instance = new DbConnection();
        if (connesso != true)
            connetti("simplebooking", "root", "admin");
        return instance;
    }

    // Apre la connessione con il Database
    private static boolean connetti(String nomeDB, String nomeUtente, String pwdUtente) {

        connesso = false;
        try {

            // Carico il driver JDBC per la connessione con il database MySQL
            Class.forName("com.mysql.jdbc.Driver");
            db = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nomeDB + "?user=" + nomeUtente + "&password=" + pwdUtente+"&useLegacyDatetimeCode=false&serverTimezone=UTC");
            connesso=true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connesso;
    }

    // Esegue una query di selezione dati sul Database
    // query: una stringa che rappresenta un'istruzione SQL di tipo SELECT da eseguire
    // colonne: il numero di colonne di cui sar� composta la tupla del risultato
    // ritorna un ArrayList contenente tutte le tuple del risultato
    public ArrayList<String[]> eseguiQuery(String query) {
        ArrayList<String[]> v = null;
        String [] record;
        int colonne = 0;
        try {
            Statement stmt = db.createStatement();     // Creo lo Statement per l'esecuzione della query
            ResultSet rs = stmt.executeQuery(query);   // Ottengo il ResultSet dell'esecuzione della query
            v = new ArrayList<String[]>();
            ResultSetMetaData rsmd = rs.getMetaData();
            colonne = rsmd.getColumnCount();

            while(rs.next()) {   // Creo il vettore risultato scorrendo tutto il ResultSet
                record = new String[colonne];
                for (int i=0; i<colonne; i++) record[i] = rs.getString(i+1);
                v.add( (String[]) record.clone() );
            }
            rs.close();     // Chiudo il ResultSet
            stmt.close();   // Chiudo lo Statement
        } catch (Exception e) { e.printStackTrace(); }

        return v;
    }

    public void addFoto(File file, String sql) {

        try {
            FileInputStream inputStream = new FileInputStream(file);

            PreparedStatement statement = db.prepareStatement(sql);
            statement.setBlob(1, inputStream);
            statement.executeUpdate();
            statement.close();   // Chiudo lo Statement
        } catch(Exception e) { e.printStackTrace(); }
    }


    public byte[] getFoto(String query) {
        //query = "select F.foto from foto as F where F.idfoto=1;"
        byte[] blob=null;
        try {
            Statement stmt = db.createStatement();     // Creo lo Statement per l'esecuzione della query
            ResultSet rs = stmt.executeQuery(query);   // Ottengo il ResultSet dell'esecuzione della query

            int rowcount = 0;
            if (rs.last()) {
                rowcount = rs.getRow();
                rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
            }
            if(rowcount==1) {
                rs.next();
                blob = rs.getBytes(1);
            }
            rs.close();     // Chiudo il ResultSet
            stmt.close();   // Chiudo lo Statement
        } catch (Exception e) { e.printStackTrace(); }

        return blob;
    }

    // Esegue una query di aggiornamento sul Database
    // query: una stringa che rappresenta un'istuzione SQL di tipo UPDATE da eseguire
    // ritorna TRUE se l'esecuzione � adata a buon fine, FALSE se c'� stata un'eccezione
    public boolean eseguiAggiornamento(String query) {
        int numero ;
        boolean risultato = false;
        try {
            Statement stmt = db.createStatement();
            numero = stmt.executeUpdate(query);
            risultato = true;
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            risultato = false;
        }
        return risultato;
    }

    public void disconnetti() {
        try {
            db.close();    // Chiude la connessione con il Database
            connesso = false;
        } catch (Exception e) { e.printStackTrace(); }
    }

    public boolean isConnesso() {
        return connesso;      // Ritorna TRUE se la connessione con il Database � attiva
    }
}