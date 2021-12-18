package com.ArdhiJmartBO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class untuk mendefinisikan Store
 * @author Ardhi Putra
 */
public class Store 
{
    public String name;
    public double balance;
    public String address;
    public String phoneNumber;
    public static String REGEX_PHONE = "^[0-9]{9,12}\b";
    public static String REGEX_NAME = "\\w+\\s{1}\\w+\\s{1}\\w+";

    /**
     * Constructor dari store
     * @param name nama dari store yang dibuat
     * @param address address dari store yang dibuat
     * @param phoneNumber nomor telpon dari store yang dibuat
     * @param balance balance dari store
     */
    public Store(String name, String address, String phoneNumber, double balance)
    {
        
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    /**
     * Method untuk mengembalikan komponen Store dalam bentuk string
     * @return komponen store dalam bentuk string
     */
    public String toString(){
        return 
        "Name: "+ name +
        "\naddress: " + address +
        "\nphoneNumber: " + phoneNumber;
    }

    /**
     * Method untuk memvalidasi apakah nama dan telpon sudah sesuai
     * @return mengembalikan true bila sesuai dan false bila tidak
     */
    public boolean validate()
    {
        Pattern telp = Pattern.compile(REGEX_PHONE);
        Pattern nama = Pattern.compile(REGEX_NAME);
        Matcher matchtelp = telp.matcher(phoneNumber);
        Matcher matchnama = nama.matcher(name);
        boolean telpbener = matchtelp.find();
        boolean namabener = matchnama.find();
        if ( telpbener == true && namabener == true)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
}
