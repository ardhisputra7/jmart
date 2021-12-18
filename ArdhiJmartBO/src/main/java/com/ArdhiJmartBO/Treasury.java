package com.ArdhiJmartBO;

/**
 * Class yang digunakan untuk mengatur segala pembayaran pada jmart
 */
public class Treasury
{
    public static final double COMMISSION_MULTIPLIER = 0.05;
    public static final double BOTTOM_PRICE = 20000.0;
    public static final double BOTTOM_FEE = 1000;

    /**
     * Method untuk mendapat harga yang sudah dimasukkan discount dan admin fee
     * @param price harga dari product
     * @param discount discount yang didapat
     * @return harga dalam bentuk double
     */
    public static double getAdjustedPrice(double price, double discount)
    {
        return getDiscountedPrice(price, discount) + getAdminFee(price, discount);
        
    }

    /**
     * Method untuk mendapat harga yang sudah dimasukkan admin fee
     * @param price harga dari product
     * @param discount discount yang didapat
     * @return harga dalam bentuk double
     */
    public static double getAdminFee(double price, double discount)
    {
        if (getDiscountedPrice(price, discount) < BOTTOM_PRICE)
        {
            return BOTTOM_FEE;
        }
        else
        {
            return getDiscountedPrice(price, discount)*COMMISSION_MULTIPLIER;
        }
    }

    /**
     * Method untuk mendapat harga yang sudah dimasukkan discount
     * @param price harga dari product
     * @param discount discount yang didapat
     * @return harga dalam bentuk double
     */
    private static double getDiscountedPrice(double price, double discount)
    {
        
        if ( discount >= 100)
        {
            discount = 100;
        }
        
        price = price - (price*discount/100);
        return price;
    }
}
