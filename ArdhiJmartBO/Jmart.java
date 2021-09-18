package ArdhiJmartBO;

public class Jmart
{
    public static void main(String[] args)
    {    
        
    }
    
    public static Product create()
    {
        return new Product("HP", 4, false, new PriceTag(25000,50),ProductCategory.GADGET);
    }
    /*
    public static int getPromo()
    {
        return 0;
    }
    
    public static String getCustomer()
    {
        return "oop";
    }
    
    public static float getDiscountPercentage(int before, int after)
    {
        float discountPercentage;
        
        if (before>after)
        {
            discountPercentage = before - after;
            discountPercentage = (discountPercentage * 100) / before;
        }
        else
        {
            discountPercentage = 0;
        }
        return discountPercentage;
    }
    
    public static int getDiscountedPrice(int price, float discountPercentage)
    {
        int diskon = (int)discountPercentage;
        int discountedPrice;
        if (discountPercentage<100)
        {
            discountedPrice = price - (price * diskon / 100);
        }
        else
        {
            discountedPrice = 0;
        }
        return discountedPrice;
    }
    
    public static int getOriginalPrice(int discountedPrice, float  discountPercentage)
    {
       return (int)(discountedPrice * 100 / (100 - discountPercentage));  
    }
    
    public static float getCommissionMultiplier()
    {
         float commisionMultiplier = 0.05f;
         return commisionMultiplier;
    }
    
    public static int getAdjustedPrice(int price)
    {
         return price + (int)(price * getCommissionMultiplier());
    }
    
    public static int getAdminFee(int price)
    {
        return (int)(price * getCommissionMultiplier());
    }
    */
}
