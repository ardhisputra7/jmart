package ArdhiJmartBO;


public class Coupon
{
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
        // put your code here
        return used;
    }
    
    public boolean canApply(PriceTag priceTag)
    {
        if  (priceTag.getAdjustedPrice()>= minimum && used == false )
        {
            return true;
    
        }
        else {
            return false;
        }
    }
    
    public double apply(PriceTag priceTag)
    {
        used = true;
        if(type == Type.DISCOUNT)
        {
            return priceTag.getAdjustedPrice() - (priceTag.getAdjustedPrice() * cut / 100);
        }
        else if(type == Type.REBATE)
        {
            return priceTag.getAdjustedPrice() - cut;
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
