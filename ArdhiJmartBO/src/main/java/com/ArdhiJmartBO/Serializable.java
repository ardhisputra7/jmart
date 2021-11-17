package com.ArdhiJmartBO;
import java.util.HashMap;

public class Serializable implements Comparable <Serializable>
{
    // instance variables - replace the example below with your own
    public final int id;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>(); 
    
    protected Serializable()
    {
    	Integer counter = mapCounter.get(getClass());
    	counter = counter == null ? 0 : counter + 1;
    	mapCounter.put(getClass(), counter);
    	this.id = counter;
    }
    
    public int compareTo (Serializable other)
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
    	if (object instanceof Serializable) {
    		object = (Serializable) object;
    		if (this.id == ((Serializable)object).id) {
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
    
    public boolean equals (Serializable recognizable)
    {
        return id == recognizable.id;
    }
    
    public static Integer getClosingId (Class<Serializable> clazz)
    {
    	return mapCounter.get(clazz);
    }
    
    public static Integer setClosingId (Class<Serializable> clazz, int id)
    {
    	return mapCounter.put(clazz,id);
  
    }
}
