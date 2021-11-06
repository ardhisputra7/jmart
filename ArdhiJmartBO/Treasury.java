package ArdhiJmartBO;

public class Treasury
{
    // instance variables - replace the example below with your own
    public static final double COMMISSION_MULTIPLIER = 0.05;
    public static final double BOTTOM_PRICE = 20000.0;
    public static final double BOTTOM_FEE = 1000;
    
    public static double getAdjustedPrice(double price, double discount)
    {
        // initialise instance variables
        return getDiscountedPrice(price, discount) + getAdminFee(price, discount);
        
    }
    
    public static double getAdminFee(double price, double discount)
    {
        // initialise instance variables
        if (getDiscountedPrice(price, discount) < BOTTOM_PRICE)
        {
            return BOTTOM_FEE;
        }
        else
        {
            return getDiscountedPrice(price, discount)*COMMISSION_MULTIPLIER;
        }
    }
    
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
