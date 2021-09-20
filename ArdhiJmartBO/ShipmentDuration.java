package ArdhiJmartBO;

public class ShipmentDuration
{
    // instance variables - replace the example below with your own
    public static final ShipmentDuration INSTANT = new ShipmentDuration(1<<0);
    public static final ShipmentDuration SAME_DAY = new ShipmentDuration(1<<1);;
    public static final ShipmentDuration NEXT_DAY = new ShipmentDuration(1<<2);;
    public static final ShipmentDuration REGULER = new ShipmentDuration(1<<3);;
    public static final ShipmentDuration KARGO = new ShipmentDuration(1<<4);;
    private int bit;
    
    /**
     * Constructor for objects of class ShipmentDuration
     */
    private ShipmentDuration(int bit)
    {
        // initialise instance variables
        this.bit = bit;
    }
    
    public ShipmentDuration(ShipmentDuration... args)
    {
        // initialise instance variables
        
    }

   
    public boolean isDuration(ShipmentDuration reference)
    {
        // put your code here
        return true;
    }
}
