package ArdhiJmartBO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Recognizable implements FileParser
{
    // instance variables - replace the example below with your own
    public static final String REGEX_EMAIL = "^\\w+([\\.]?[&\\*~\\w+])*@\\w+([\\.-]?)*(\\.\\w{2,3})+$";
    public static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=\\S+$)(?=.*[a-z])(?=.*[A-Z]).{8,}$";
    public String name;
    public String email;
    public String password;
    
    public Account(int id, String name, String email, String password)
    {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public boolean validate(){
        Pattern emailpat = Pattern.compile(REGEX_EMAIL);
        Pattern passpat = Pattern.compile(REGEX_PASSWORD);
        Matcher matchemail = emailpat.matcher(email);
        Matcher matchpass = passpat.matcher(password);
        boolean emailbener = matchemail.find();
        boolean passbener = matchpass.find();
        if ( emailbener == true && passbener == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean read(String content)
    {
        // put your code here
        return false;
    }
}
