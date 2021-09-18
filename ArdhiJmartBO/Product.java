package ArdhiJmartBO;


public class Product
{
    // instance variables - replace the example below with your own
    private int idCounter;
    public int id;
    public String name;
    public int weight;
    public boolean conditionUsed;
    public PriceTag priceTag;
    public ProductCategory category;
    public ProductRating rating;
    
    /**
     * Constructor for objects of class Product
     */
    public Product(String name,int weight,boolean conditionUsed,PriceTag prieTage,ProductCategory category)
    {
        // initialise instance variables
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category =category;
        this.rating = new ProductRating();
        idCounter++;
    }

    
}
