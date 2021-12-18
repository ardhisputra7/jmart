package com.ArdhiJmartBO;
import com.ArdhiJmartBO.dbjson.Serializable;

import java.util.Date;

/**
 * Abstract Class untuk mendefinisikan Invoice
 * extends Seriazable
 * @author Ardhi Putra
 */
public abstract class Invoice extends Serializable
{
    public final Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;

    /**
     * Constructor untuk invoice
     * @param buyerId id dari pembeli
     * @param productId id dari product yang dibeli
     */
    protected Invoice(int buyerId, int productId)
    {
        this.buyerId = buyerId;
        this.productId = productId;
        date = new Date();
        complaintId = -1;
        rating = Rating.NONE;
    }

    /**
     * abstract method untuk mendapat harga total
     * @param product object dari product
     * @return melakukan return harga total dalam double
     */
    public abstract double getTotalPay(Product product);

    /**
     * enum untuk rating dari invoice
     */
    public static enum Rating
    {
        NONE, BAD, NEUTRAL, GOOD
    }

    /**
     * enum untuk status dari invoice
     */
    public static enum Status
    {
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED, DELIVERED, FAILED
    }
    
}
