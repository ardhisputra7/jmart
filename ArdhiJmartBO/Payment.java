package ArdhiJmartBO;

import java.util.Date;
import ArdhiJmartBO.Invoice.Status;
import java.util.ArrayList;

public class Payment extends Invoice
{
	ArrayList<Record> history;
    public int productCount;
    public Shipment shipment;
    
    public Payment(int buyerId, int productId, int productCount, Shipment shipment)
    {
        super(buyerId,productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }

    
    public double getTotalPay(Product product){
    	return product.price - (product.price*product.discount);
    }
    
    public class Record
    {
    	public Status status;
    	public final Date date;
    	public String message;
    	
    	public Record (Status status, String message)
    	{
    		this.status = status;
    		this.message = message;
    		date = new Date();
    	}
    }
    
}
