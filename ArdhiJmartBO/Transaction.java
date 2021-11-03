package ArdhiJmartBO;

public abstract class Transaction extends Recognizable
{
    // instance variables - replace the example below with your own
    public String time;
    public int buyerId;
    public int storeId;
    public ProductRating rating;
    
    protected Transaction(int id, int buyerId, int storeId)
    {
       
        this.buyerId = buyerId;
        this.storeId = storeId;
        
    }
    
    protected Transaction(int id, Account buyer, Store store)
    {
    	
        
    }
       
    public static enum Rating
    {
        NONE, BAD, NEUTRAL, GOOD
    }
}
