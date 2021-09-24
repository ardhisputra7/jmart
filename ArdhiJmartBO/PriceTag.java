package ArdhiJmartBO;

public class PriceTag
{
    // instance variables - replace the example below with your own
    public static final double COMMISSION_MULTIPLIER = 0.05;
    public static final double BOTTOM_PRICE = 20000.0;
    public static final double BOTTOM_FEE = 1000;
    public double discount;
    public double price;
    /**
     * Constructor for objects of class PriceTag
     */
    public PriceTag(double price)
    {
        // initialise instance variables
        this.discount = 0;
        this.price = price;
    }

    public PriceTag(double price, double discount)
    {
        // initialise instance variables
        this.discount = discount;
        this.price = price;
    }
    
    public double getAdjustedPrice()
    {
        // initialise instance variables
        return getDiscountedPrice() + getAdminFee();
        
    }
    
    public double getAdminFee()
    {
        // initialise instance variables
        if (getDiscountedPrice() < BOTTOM_PRICE)
        {
            return BOTTOM_FEE;
        }
        else
        {
            return getDiscountedPrice()*COMMISSION_MULTIPLIER;
        }
    }
    
    private double getDiscountedPrice()
    {
        
        if (discount>=100)
        {
            discount = 100;
        }
        else
        {
            discount = discount;
        }
        
        price = price - (price*discount/100);
        return price;
    }
}
