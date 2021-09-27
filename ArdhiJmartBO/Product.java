package ArdhiJmartBO;


public class Product implements FileParser
{
    // instance variables - replace the example below with your own
    public int storeId;
    public String name;
    public int weight;
    public boolean conditionUsed;
    public PriceTag priceTag;
    public ProductCategory category;
    public ProductRating rating;
    public Shipment.MultiDuration multiDuration;
    
    public Product(int id, int storeId, String name, int weight, boolean conditionUsed,PriceTag priceTag, ProductCategory category, Shipment.MultiDuration multiDuration)
    {
        // initialise instance variables
        this.storeId = storeId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category = category;
        this.rating = rating;
        this.multiDuration = multiDuration;
    }

    public boolean read(String content)
    {
        // put your code here
        return false;
    }
    
    public String toString(){
        return 
        "Name: "+ name +
        "\nWeight: " + weight +
        "\nconditionUsed: " + conditionUsed +
        "\npriceTag: " + priceTag +
        "\ncategory: " + category +
        "\nrating: " + rating +
        "\nstoreId: " + storeId;
    }
}
