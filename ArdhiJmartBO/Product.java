package ArdhiJmartBO;


public class Product
{
    // instance variables - replace the example below with your own
    public String name;
    public int weight;
    public boolean conditionUsed;
    public PriceTag priceTag;
    public ProductCategory category;
    public ProductRating rating;
    public int storeid;
    
    public Product(String name,int weight,boolean conditionUsed,PriceTag prieTage,ProductCategory category)
    {
        // initialise instance variables
        this.storeid = storeid;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category =category;
        this.rating = new ProductRating();
    }

    
}
