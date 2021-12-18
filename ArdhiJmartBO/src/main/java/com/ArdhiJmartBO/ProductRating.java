package com.ArdhiJmartBO;

/**
 * Class untuk mendefinisikan Product Rating
 */
public class ProductRating
{
    private long total;
    private long count;

    /**
     * Constuctor untuk Product Rating
     */
    public ProductRating()
    {
        this.total = 0;
        this.count = 0;
    }

    /**
     * Method untuk memasukkan
     * @param rating
     */
    public void insert(int rating)
    {
        this.total = total + rating;
        count++;
    }
    
    public double getAverage()
    {
        if (count == 0)
        {
            return 0;
        }
        else 
        {
           return total/count; 
        }
    }
    
    public long getCount()
    {
        return count;
    }
    
    public long getTotal()
    {
        return total;
    }
}
