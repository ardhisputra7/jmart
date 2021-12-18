package com.ArdhiJmartBO.controller;

import com.ArdhiJmartBO.*;
import com.ArdhiJmartBO.dbjson.JsonAutowired;
import com.ArdhiJmartBO.dbjson.JsonTable;
import com.ArdhiJmartBO.dbjson.Serializable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Controller class untuk product
 * Menggunakan Spring framework
 * Mengimplementasikan BasicGetController
 * @author Ardhi Putra
 *
 */
@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product>
{
    @JsonAutowired(filepath = "C:/Java/Jmart/resource/product.json", value = Product.class)
    /**
     * menyimpan product yang dibuat pada JsonTable
     */
    public static JsonTable<Product> productTable;

    /**
     * Method untuk proses ketika melakukan create pada product
     * @param accountId id dari store atau account dimana product dibuat
     * @param name nama product
     * @param weight berat product
     * @param conditionUsed kondisi baru atau lama product
     * @param price harga dari product
     * @param discount discount dari product
     * @param category kategori dari product
     * @param shipmentPlans shipment plan dari product
     * @return null
     */
    @PostMapping("/create")
    Product create(
             @RequestParam int accountId,
             @RequestParam String name,
             @RequestParam int weight,
             @RequestParam boolean conditionUsed,
             @RequestParam double price,
             @RequestParam double discount,
             @RequestParam ProductCategory category,
             @RequestParam byte shipmentPlans)
        {
            Predicate<Account> predId = account -> account.id == accountId;
            Predicate<Account> predStore = account -> account.store != null;
            if(Algorithm.exists(AccountController.accountTable.iterator(), predId) &&
                Algorithm.exists(AccountController.accountTable.iterator(), predStore)) {
            Product product = new Product(accountId, name, weight,
                    conditionUsed, price, discount, category, shipmentPlans);
            productTable.add(product);
            return product;
        }
        return null;
    }

    /**
     * method untuk mengembalikan jsonTable dari product
     * @return productTable
     */
    @Override
    public JsonTable<Product> getJsonTable() {
        return productTable;
    }

    /**
     * Method untuk mengembalikan product yang ada pada sebuah store
     * @param id id dari store
     * @param page halaman dari product
     * @param pageSize ukuran jumlah product tiap halaman
     * @return hasil yang sudah dipaginasi
     */
    @GetMapping("/{id}/store")
    List<Product> getProductByStore (
            @PathVariable int id,
            @RequestParam int page,
            @RequestParam int pageSize){

        Predicate<Product> predicates = prod -> true;
        List<Product> list = new ArrayList<>();

        for(Product product : getJsonTable()) {
            list.add(product);
        }

        return Algorithm.<Product>paginate(list, page, pageSize, predicates);
    }

    /**
     * method untuk melakukan filter pada product
     * @param page halaman dari product
     * @param pageSize ukuran jumlah product tiap halaman
     * @param accountId accountId dari product
     * @param search nama product yang dicari
     * @param minPrice harga minimum product yang dicari
     * @param maxPrice harga maximum product yang dicari
     * @param category jenis kategori product yang dicari
     * @return mengembalikan product yang sudah di filter sesuai dengan ketentuan
     */
	@GetMapping("/getFiltered")
    List<Product> getProductFiltered(@RequestParam int page, @RequestParam int pageSize, @RequestParam int accountId,
                                     @RequestParam String search, @RequestParam int minPrice, @RequestParam int maxPrice,
                                     @RequestParam ProductCategory category) {
        Predicate<Product> filter = filtered -> (filtered.accountId == accountId || filtered.accountId != accountId)
                                             && filtered.name.toLowerCase().contains((search.toLowerCase()))
                                             && filtered.price >= minPrice
                                             && filtered.price <= maxPrice
                                             && filtered.category.equals(category);

        List<Product> list = new ArrayList<>();
        for(Product product : getJsonTable()) {
            list.add(product);
        }

        return Algorithm.paginate(list, page, pageSize, filter);
    }
}
