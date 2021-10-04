package ArdhiJmartBO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Store extends Recognizable implements FileParser
{
    public String name;
    public String address;
    public String phoneNumber;
    public static String REGEX_PHONE = "^[0-9]{9,12}\b";
    public static String REGEX_NAME = "\\w+\\s{1}\\w+\\s{1}\\w+";
    
    public Store(int accountId, String name, String address, String phoneNumber)
    {
        super (accountId);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    public Store(Account account, String name, String address, String phoneNumber)
    {
        super (account.id);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        
    }
    
    public boolean read(String content)
    {
        // put your code here
        return false;
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
