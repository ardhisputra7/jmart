package ArdhiJmartBO;

public class Complaint extends Recognizable implements FileParser
{
    public String date;
    public String desc;

    public Complaint(int id, String desc)
    {
        super(id);
        this.desc = desc;
        desc = "satu";
    }

    public boolean read(String content)
    {
        return false;
    }
}
