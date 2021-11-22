package com.ArdhiJmartBO;


import com.ArdhiJmartBO.dbjson.Serializable;

public class Coupon extends Serializable {
    // instance variables - replace the example below with your own
    public final String name;
    public final int code;
    public final double cut;
    public final Type type;
    public final double minimum;
    private boolean used;
    
    /**
     * Constructor for objects of class Coupon
     */
    public Coupon(String name, int code, Type type, double cut, double minimum)
    {
        // initialise instance variables
        this.name = name;
        this.code = code;
        this.cut = cut;
        this.type = type;
        this.minimum = minimum;
        this.used = false;
    }

    public boolean isUsed()
    {
        return used;
    }
    
    public boolean canApply(double price, double discount)
    {
        if  (Treasury.getAdjustedPrice(price, discount)>= minimum && used == false )
        {
            return true;
    
        }
        else {
            return false;
        }
    }
    
    public double apply(double price, double discount)
    {
        used = true;
        if(type == Type.DISCOUNT)
        {
            return Treasury.getAdjustedPrice(price, discount) - (Treasury.getAdjustedPrice(price, discount) * cut / 100);
        }
        else if(type == Type.REBATE)
        {
            return Treasury.getAdjustedPrice(price, discount) - cut;
        }
        else 
        {
            return 0;
        }
    }
    
    public static enum Type
    {
        DISCOUNT, REBATE
    }
}
