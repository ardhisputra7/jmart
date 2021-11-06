package ArdhiJmartBO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import com.google.gson.*;

public class Jmart
{   
    class Country
    {
    	public String name;
    	public int population;
    	public List<String> listOfStates;
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
	
	public static List<Product> filterByPrice (List<Product> list, double minPrice, double maxPrice)
	{
		List<Product> priceFilterList = new ArrayList<>();
  	  
  	  	for(Product lists : list) {
  	  		if(minPrice == 0.0) {
  	  			priceFilterList.add(lists);
  	  		}
  	  		
  	  	}	
  	  
  	  return priceFilterList;
	}
	
	public static List<Product> read (String filepath)
	{
    	Gson gson = new Gson();
    	try
    	{
    		BufferedReader br = new BufferedReader (new FileReader(filepath));
    		Country input = gson.fromJson(br,  Country.class);
    		System.out.println("name: " + input.name);
    		System.out.println("population: " + input.population);
    		System.out.println("states:");
    		input.listOfStates.forEach(state -> System.out.println(state));
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    	return null;
	}
	
    public static void main(String[] args)
    {  
    	System.out.println("account id:" + new Account(null, null, null, -1).id);
    	System.out.println("account id:" + new Account(null, null, null, -1).id);
    	System.out.println("account id:" + new Account(null, null, null, -1).id);
    	
    	System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
    	System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
    	System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
    	
    	try
    	{
    	List<Product> list = read("/Java/Jmart/lib/randomProductList.json");
    	List<Product> filtered = filterByPrice(list, 0.0, 20000.0);
    	filtered.forEach(product -> System.out.println(product.price));
    	}
    	catch (Throwable t)
    	{
    		t.printStackTrace();
    	}
    	/*
    	String filepath ="/Java/Jmart/lib/city.json";
    	Gson gson = new Gson();
    	try
    	{
    		BufferedReader br = new BufferedReader (new FileReader(filepath));
    		Country input = gson.fromJson(br,  Country.class);
    		System.out.println("name: " + input.name);
    		System.out.println("population: " + input.population);
    		System.out.println("states:");
    		input.listOfStates.forEach(state -> System.out.println(state));
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    	*/
    }

}
