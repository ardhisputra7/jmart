package ArdhiJmartBO;

public class Complaint extends Transaction implements FileParser
{
    public int paymentId;
    public String desc;

    public Complaint(int id, Payment payment, String desc)
    {
        super(id,0, 0);
        
        this.desc = desc;
    }

    public Complaint(int id, int buyerId, int storeId, int paymentId, String desc)
    {
        // put your code here
        super(id,buyerId,storeId);
        this.paymentId = paymentId;
        this.desc = desc;
    }
    
    public boolean read(String content)
    {
        return false;
    }
}
