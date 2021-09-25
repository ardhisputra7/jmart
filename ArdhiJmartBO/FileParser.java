package ArdhiJmartBO;

public interface FileParser
{
    boolean read (String content);
    
    default Object write (){
        return null;
    }
    
    default Object newInstance(String content){
        return null;
    }
}
