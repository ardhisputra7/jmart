package com.ArdhiJmartBO;
import com.ArdhiJmartBO.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class untuk mendefinisikan Account
 * extends Seriazable
 * @author Ardhi Putra
 *
 */
public class Account extends Serializable
{
    /**
     * regex untuk email
     */
    public static final String REGEX_EMAIL = "^\\w+([\\.]?[&\\*~\\w+])*@\\w+([\\.-]?)*(\\.\\w{2,3})+$";

    /**
     * regex untuk password
     */
    public static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=\\S+$)(?=.*[a-z])(?=.*[A-Z]).{8,}$";

    public String name;
    public String email;
    public String password;
    public double balance;
    public Store store;

    /**
     * Constructor dari Account
     * @param name
     * @param email
     * @param password
     * @param balance
     */
    public Account(String name, String email, String password, double balance)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
		this.store = null;
    }

    /**
     * Memvalidasi apakah email dan password sesuai regex
     * @return mengembalikan true bila sesuai regex dan false bila tidak
     */
    public boolean validate()
    {
        Pattern emailpat = Pattern.compile(REGEX_EMAIL);
        Pattern passpat = Pattern.compile(REGEX_PASSWORD);
        Matcher matchemail = emailpat.matcher(email);
        Matcher matchpass = passpat.matcher(password);
        boolean emailbener = matchemail.find();
        boolean passbener = matchpass.find();
        
        if ( emailbener == true && passbener == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * method untuk mengembalikan komponen account dalam string
     * @return data-data dalam bentuk string
     */
    public String toString() {
    	return "nama:" + name + 
    		   "\nemail: "+ email + 
    		   "\npassword: " + password + 
    		   "\nbalance: "+ balance +
                "\nstore: "+ store +
                "\n";
    }
    
}
