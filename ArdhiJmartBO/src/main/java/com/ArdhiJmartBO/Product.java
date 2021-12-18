package com.ArdhiJmartBO;
import com.ArdhiJmartBO.dbjson.Serializable;

/**
 * Class untuk mendefinisikan Product
 * extends Seriazable
 * @author Ardhi Putra
 *
 */
public class Product extends Serializable {

    public int accountId;
    public ProductCategory category;
    public boolean conditionUsed;
    public double discount;
    public String name;
    public double price;
    public byte shipmentPlans;
    public int weight;

    /**
     * Constructor dari product
     * @param accountId id dari store atau account dimana product dibuat
     * @param name nama product
     * @param weight berat product
     * @param conditionUsed kondisi baru atau lama product
     * @param price harga dari product
     * @param discount discount dari product
     * @param category kategori dari product
     * @param shipmentPlans shipment plan dari product
     */
    public Product(int accountId, String name, int weight, boolean conditionUsed, double price, double discount, ProductCategory category, byte shipmentPlans)
    {
        this.accountId = accountId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.shipmentPlans = shipmentPlans;
        
    }

    /**
     * Method untuk mengubah isi dari product menjadi string
     * @return string dari komponen pada product
     */
    public String toString(){
        return 
        "Name: "+ name +
        "\nWeight: " + weight +
        "\nconditionUsed: " + conditionUsed +
        "\nprice: " + price +
        "\nDiscount: " + discount +
        "\nCategory: " + category +
        "\nShipmentPlans: " + shipmentPlans;
    }
}
