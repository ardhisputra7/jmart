package ArdhiJmartBO;
import java.util.Date;

public abstract class Invoice extends Serializable 
{
    public final Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    
    protected Invoice(int buyerId, int productId)
    {
        this.buyerId = buyerId;
        this.productId = productId;
        date = new Date();
        complaintId = -1;
        rating = Rating.NONE;
    }
    
    public abstract double getTotalPay(Product product);
    
    public static enum Rating
    {
        NONE, BAD, NEUTRAL, GOOD
    }
    
    public static enum Status
    {
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED, DELIVERED, FAILED
    }
    
}
