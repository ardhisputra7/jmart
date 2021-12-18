package com.ArdhiJmartBO;


import com.ArdhiJmartBO.dbjson.Serializable;

/**
 * Class untuk mendefinisikan Coupon
 * extends Seriazable
 * @author Ardhi Putra
 *
 */
public class Coupon extends Serializable {

    public final String name;
    public final int code;
    public final double cut;
    public final Type type;
    public final double minimum;
    private boolean used;
    
    /**
     * Constructor untuk coupon
     */
    public Coupon(String name, int code, Type type, double cut, double minimum)
    {
        this.name = name;
        this.code = code;
        this.cut = cut;
        this.type = type;
        this.minimum = minimum;
        this.used = false;
    }

    /**
     * Method untuk menentukan apakah coupon sudah digunakan atau belum
     * @return mengembalikan true bila sudah dan false ketika belum
     */
    public boolean isUsed()
    {
        return used;
    }

    /**
     * Method untuk menentukan apakah coupon bisa digunakan atau tidak
     * @param price harga barang yang ingin di pasang coupon
     * @param discount besar diskon dari coupon
     * @return mengembalikan true bila bisa digunakand dan false ketika tidak
     */
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

    /**
     * Method untuk memasang coupon
     * @param price harga barang yang ingin di pasang coupon
     * @param discount besar diskon dari coupon
     * @return mengembalikan harga yang didiskon bila bisa dipasang
     */
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

    /**
     * Enum untuk type dari coupon
     */
    public static enum Type
    {
        DISCOUNT, REBATE
    }
}
