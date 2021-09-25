package ArdhiJmartBO;


public class Recognizable
{
    // instance variables - replace the example below with your own
    public final int id;

    protected Recognizable(int id)
    {
      this.id = id;  
    }
    
    public boolean equals (Object object)
    {
        Recognizable recognizable = (Recognizable) object;
        return object instanceof Recognizable && ((Recognizable) object).id == id;
            
    }
    
    public boolean equals (Recognizable recognizable)
    {
        return id == recognizable.id;
    }
}
