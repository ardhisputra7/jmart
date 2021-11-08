package ArdhiJmartBO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

public class Jmart
{   
    class Country
    {
    	public String name;
    	public int population;
    	public List<String> listOfStates;
    }
    
    public static List<Product> filterByAccountId (List<Product> list, int accountId, int page, int pageSize)
	{
    	 List<Product> accountIdFilterList = new ArrayList<>();
    	 Predicate<Product> pred = product -> product.accountId == accountId;
    	 accountIdFilterList = paginate(list,page,pageSize, pred);
    	 return accountIdFilterList;
	}
    
    public static List<Product> filterByCategory (List<Product> list, ProductCategory category)
	{
    	 List<Product> categoryFilterList = new ArrayList<>();
    	  
    	  for(Product lists : list) {
    		  if(lists.category == category) {
    			  categoryFilterList.add(lists);
    		  }
    	  }
    	  
    	  return categoryFilterList;
	}
	
    public static List<Product> filterByName (List<Product> list, String search, int page, int pageSize)
	{
    	 List<Product> nameFilterList = new ArrayList<>();
    	 Predicate<Product> pred = product -> product.name.toLowerCase().contains(search.toLowerCase());
    	 nameFilterList = paginate(list,page,pageSize, pred);
    	  return nameFilterList;
	}
    
	public static List<Product> filterByPrice (List<Product> list, double minPrice, double maxPrice)
	{
		List<Product> priceFilterList = new ArrayList<>();
  	  
  	  	for(Product lists : list) {
  	  		if (minPrice == 0.0 && lists.price <= maxPrice){
  	  			priceFilterList.add(lists);
  	  		}
  	  		else if (maxPrice == 0.0 && lists.price >= minPrice) {
	  			priceFilterList.add(lists);
	  		}
  	  		else if (lists.price >= minPrice && lists.price <= maxPrice) {
  	  			priceFilterList.add(lists);
  	  		}
  	  	}	
  	  
  	  return priceFilterList;
	}
	
	private static List<Product> paginate (List<Product> list, int page, int pageSize, Predicate<Product> pred)
	{
		List<Product> pagination = new ArrayList<>();
		
		for(Product lists : list) {
			if (pred.predicate(lists)) {
				pagination.add(lists);
			}
		}
	    
		if (pageSize <= 0 || page < 0) {
			throw new IllegalArgumentException("Page size tidak valid");
		}
	    int fromIndex = (page) * pageSize;
	    if(pagination == null || pagination.size() <= fromIndex){
	        return Collections.emptyList();
	    }
	    
	    // toIndex exclusive
	    return pagination.subList(fromIndex, Math.min(fromIndex + pageSize, pagination.size()));
	}
	
	public static List<Product> read(String filepath) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(filepath));
        List<Product> list = new ArrayList<Product>();
        try{
            Gson gson = new Gson();
            reader.beginArray();
            while(reader.hasNext()){
                Product product = gson.fromJson(reader, Product.class);
                list.add(product);
            }
            reader.endArray();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return list;
    }
	
    public static void main(String[] args)
    {  
    	try
    	{
    	List<Product> list = read("/Java/Jmart/lib/randomProductList.json");
    	/*List<Product> filtered = filterByPrice(list, 13000.0, 20000.0);
    	filtered.forEach(product -> System.out.println(product.price));
    	*/
    	List<Product> filtered = filterByName(list, "gtx", 1, 5);
    	filtered.forEach(product -> System.out.println(product.name));
    	
    	List<Product> filteredId = filterByAccountId(list, 1, 0, 5);
    	filteredId.forEach(product -> System.out.println(product.name));
    	}
    	catch (Throwable t)
    	{
    		t.printStackTrace();
    	}
    }

}
