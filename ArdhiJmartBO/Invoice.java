package ArdhiJmartBO;
import java.util.Date;
import java.util.ArrayList;

public abstract class Invoice extends Serializable 
{
    public Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    public Status status;
    public ArrayList<Record> history;
    
    protected Invoice(int buyerId, int productId)
    {
        this.buyerId = buyerId;
        this.productId = productId;
        //Date date = new Date();
        rating = Rating.NONE;
        status = Status.WAITING_CONFIRMATION;
    }
    
    public abstract double getTotalPay();
    
    public static enum Rating
    {
        NONE, BAD, NEUTRAL, GOOD
    }
    
    public static enum Status
    {
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED, FAILED
    }
    
    public class Record
    {
    	public Status status;
    	public Date date;
    	public String message;
    }
}
