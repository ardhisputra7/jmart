package com.ArdhiJmartBO;

import java.util.Date;
import com.ArdhiJmartBO.Invoice.Status;
import java.util.ArrayList;

/**
 * Class untuk mendefinisikan Payment
 * extends Invoice
 * @author Ardhi Putra
 */
public class Payment extends Invoice
{
	public ArrayList<Record> history = new ArrayList<>();
    public int productCount;
    public Shipment shipment;

    /**
     * Constructor untuk payment
     * @param buyerId
     * @param productId
     * @param productCount
     * @param shipment
     */
    public Payment(int buyerId, int productId, int productCount, Shipment shipment)
    {
        super(buyerId,productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }

    /**
     * Method untuk mendapatkan harga yang harus dibayar
     * @param product object dari product
     * @return harga yang dibayar
     */
    public double getTotalPay(Product product){
    	return product.price - (product.price*product.discount/100);
    }

    /**
     * Inner class untuk record
     */
    public static class Record
    {
    	public Status status;
    	public final Date date;
    	public String message;
    	
    	public Record (Status status, String message)
    	{
    		this.status = status;
    		this.message = message;
    		date = new Date();
    	}
    }
    
}
