package ArdhiJmartBO;
import java.util.Date;
import java.text.*;

public class Complaint extends Recognizable implements FileParser
{
    public String desc;
    public Date date;

    public Complaint(int id, String desc)
    {
        super(id);
        Date date = new Date();
        this.desc = desc;
        desc = "satu";
    }

    public boolean read(String content)
    {
        return false;
    }
    
    public String toString()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return "Complaint{date=" + dateFormat.format(date) + ", desc='" + desc + "'}";
    }
}
