package ArdhiJmartBO;


public class Recognizable implements Comparable <Recognizable>
{
    // instance variables - replace the example below with your own
    public final int id;

    protected Recognizable()
    {
      this.id = 0;  
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
    	if (object instanceof Recognizable) {
    		object = (Recognizable) object;
    		if (this.id == ((Recognizable)object).id) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    	else {
    		return false;
    	}
       
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
