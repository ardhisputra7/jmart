package com.ArdhiJmartBO;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * class untuk mendefinisikan shipment
 */
public class Shipment 
{
	public static final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat("EEE MMMM dd yyyy");
	public static final Plan INSTANT = new Plan((byte)(1 << 0));
    public static final Plan SAME_DAY = new Plan((byte)(1 << 1));
    public static final Plan NEXT_DAY = new Plan((byte)(1 << 2));
    public static final Plan REGULER = new Plan((byte)(1 << 3));
    public static final Plan KARGO = new Plan((byte)(1 << 4));
    public String address;
    public int cost;
    public byte plan;
    public String receipt;

    /**
     * Constructor untuk shipment
     * @param address
     * @param cost
     * @param plan
     * @param receipt
     */
    public Shipment(String address, int cost, byte plan, String receipt)
    {
        this.address = address;
        this.cost = cost;
        this.plan = plan;
        this.receipt = receipt;
    }

    /**
     * Method untuk mendapat perkiraan waktu datang
     * @param reference tanggal dalam bentuk object
     * @return mengembalikan waktu
     */
    public String getEstimatedArrival(Date reference)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(reference);
        if(this.plan == INSTANT.bit || this.plan == SAME_DAY.bit)
        {
            return ESTIMATION_FORMAT.format(reference);
        }
        else if(this.plan == NEXT_DAY.bit)
        {
            cal.add(Calendar.DATE,1);
            return ESTIMATION_FORMAT.format(cal.getTime());
        }
        else if(this.plan == REGULER.bit)
        {
            cal.add(Calendar.DATE,2);
            return ESTIMATION_FORMAT.format(cal.getTime());
        }
        else
        {
            cal.add(Calendar.DATE,5);
            return ESTIMATION_FORMAT.format(cal.getTime());
        }
    }


    public boolean isDuration(Plan reference) {
        if((this.plan & reference.bit) != 0)
            return true;
        else
            return false;
    }
    
    public boolean isDuration(byte object,Plan reference) {
        if((this.plan & reference.bit) != 0)
            return true;
        else
            return false;
    }

    /**
     * Inner class untuk byte pada plan
     */
    static class Plan {
    	
    	public final byte bit;
    	
    	private Plan (byte bit) {
            this.bit = bit;
        }
    }
    
    
}
