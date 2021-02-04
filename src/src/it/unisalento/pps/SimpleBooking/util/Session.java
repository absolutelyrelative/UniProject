package it.unisalento.pps.SimpleBooking.util;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.Model.Utente;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Session {
    //TODO: TEST. Pleriminary tests done.
    //Risulta utile avere un salvatore di sessione corrente. Il componente SessionHelper di Java mi risultava
    //un pochino poco-bene documentato, e altre librerie non mi sono piaciute. Quindi ecco qui un semplice
    //session object.
    private static Session instance;
    private final static String attribute_isActive = "isActive.txt";
    private final static String attribute_Utente = "Utente.txt";
    private int isActive = 0; //Default initialisation, 0 - inactive, 1 - active
    private Utente user = null; //Default initialisation
    private int userType = 0; //0 - Normal user, 1 - Seller, 2 - Vendor, 3 - Administrator

    //synchronised fa in modo che solo 1 thread è permesso
    public static synchronized Session getInstance() {
        if (instance == null)
            instance = new Session();
        return instance;
    }

    public void saveSession(Utente u) {
        createFile(attribute_Utente);
        updateFile(attribute_Utente, Integer.toString(u.getId()));
        createFile(attribute_isActive);
        updateFile(attribute_isActive, Integer.toString(1));
    }

    public void closeSession() {
        deleteFile(attribute_Utente);
        deleteFile(attribute_isActive);
    }

    public void getSession() {
        int user_id;
        this.isActive = Integer.parseInt(readFile(attribute_isActive));
        user_id = Integer.parseInt(readFile(attribute_Utente));
        this.user = UtenteDAO.getInstance().findById(user_id);
        //TODO: IMPLEMENT userType FIND
    }

    public boolean createFile(String name) { //0 - failure, 1 - success
        try {
            File myObj = new File(name);
            if (myObj.createNewFile()) {
                System.out.println("File created: ");
                return true;
            } else {
                System.out.println("File already exists.");
                return true;
            }
        } catch (IOException e) {
            System.out.println("An error occurred." + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateFile(String name, String argument) {//0 - failure, 1 - success
        try {
            FileWriter myWriter = new FileWriter(name);
            myWriter.write(argument);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred." + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFile(String name) {//0 - failure, 1 - success
        File myObj = new File(name);
        if (myObj.delete()) {
            System.out.println("Deleted the file: ");
            return true;
        } else {
            System.out.println("Failed to delete the file.");
            return false;
        }
    }

    public String readFile(String name) {//Returns read string, null if error occurred
        try {
            File myObj = new File(name);
            Scanner myReader = new Scanner(myObj);
            String data = myReader.nextLine();  //Only one line is necessary to be read, while statement is redundant
            //System.out.println("\nData read from file @ Session.readFile(): " + data);
            myReader.close();
            return data;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred." + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    public int isActive() {
        this.getSession();
        return this.isActive;
    }

    public void setActive(int active) {
        isActive = active;
    }

    public Utente getUser() {
        this.getSession();
        return user;
    }

    public void setUser(Utente user) {
        this.user = user;
    }

    public int getUserType() {
        this.getSession();
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
