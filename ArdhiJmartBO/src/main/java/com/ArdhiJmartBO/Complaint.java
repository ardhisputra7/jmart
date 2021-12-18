package com.ArdhiJmartBO;
import com.ArdhiJmartBO.dbjson.Serializable;

import java.util.Date;
import java.text.*;

/**
 * Class untuk mendefinisikan Complaint
 * extends Seriazable
 * @author Ardhi Putra
 *
 */
public class Complaint extends Serializable
{
    public String desc;
    public Date date;

    /**
     * Constructor dari complaint
     * @param desc deskripsi dari complaint
     */
    public Complaint(String desc)
    {
        this.desc = desc;
        
    }

    /**
     * Method untuk mengembalikan complaint dalam bentuk string
     * @return mereturn deskripsi complaint berserta tanggalnya
     */
    public String toString()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return "Complaint{date=" + dateFormat.format(date) + ", desc='" + desc + "'}";
    }
}
