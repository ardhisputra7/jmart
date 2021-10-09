package ArdhiJmartBO;


public class Recognizable implements Comparable <Recognizable>
{
    // instance variables - replace the example below with your own
    public final int id;

    protected Recognizable(int id)
    {
      this.id = id;  
    }
    
    public int compareTo (Recognizable other)
    {
    	if (other.id == this.id) {
    		return 0;
    	}
    	else if (other.id > this.id) {
    		return 1;
    	}
    	else {
    		return -1;
    	}
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
    
    public static int getClosingId (Class<Recognizable> clazz)
    {
    	return 0;
    }
    
    public static int setClosingId (Class<Recognizable> clazz, int id)
    {
    	
    	return 0;
    }
}
