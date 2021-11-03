package ArdhiJmartBO;
import java.util.Date;
import java.text.*;

public class Complaint extends Recognizable 
{
    public String desc;
    public Date date;

    public Complaint(String desc)
    {
    	
        this.desc = desc;
        
    }
    
    public String toString()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return "Complaint{date=" + dateFormat.format(date) + ", desc='" + desc + "'}";
    }
}
