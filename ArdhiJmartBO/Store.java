package ArdhiJmartBO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Store 
{
    public String name;
    public double balance;
    public String address;
    public String phoneNumber;
    public static String REGEX_PHONE = "^[0-9]{9,12}\b";
    public static String REGEX_NAME = "\\w+\\s{1}\\w+\\s{1}\\w+";
    
    public Store(String name, String address, String phoneNumber, double balance)
    {
        
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }
    
    
    public String toString(){
        return 
        "Name: "+ name +
        "\naddress: " + address +
        "\nphoneNumber: " + phoneNumber;
    }
    
    public boolean validate()
    {
        Pattern telp = Pattern.compile(REGEX_PHONE);
        Pattern nama = Pattern.compile(REGEX_NAME);
        Matcher matchtelp = telp.matcher(phoneNumber);
        Matcher matchnama = nama.matcher(name);
        boolean telpbener = matchtelp.find();
        boolean namabener = matchnama.find();
        if ( telpbener == true && namabener == true)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
}
