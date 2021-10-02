package ArdhiJmartBO;
import java.util.Date;

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
}
