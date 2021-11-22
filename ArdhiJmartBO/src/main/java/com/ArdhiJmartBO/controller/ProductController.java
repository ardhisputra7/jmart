package com.ArdhiJmartBO.controller;

import com.ArdhiJmartBO.*;
import com.ArdhiJmartBO.dbjson.JsonAutowired;
import com.ArdhiJmartBO.dbjson.JsonTable;
import com.ArdhiJmartBO.dbjson.Serializable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product>
{
    @JsonAutowired(filepath = "C:/Java/Jmart/resource", value = Product.class)
    public static JsonTable<Product> productTable;

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

    @Override
    public JsonTable<Product> getJsonTable() {
        return productTable;
    }
    @GetMapping("/{id}/store")
    List<Product> getProductByStore (
            @PathVariable int id,
            @RequestParam int page,
            @RequestParam int pageSize){
            Predicate<Product> pred = product -> product.accountId == id;
            return Algorithm.paginate(this.getJsonTable().iterator(), page, pageSize, pred);
    }

    @GetMapping("/getFiltered")
    List<Product> getProductFiltered (int page, int pageSize, int accountId, String search
                        ,int minPrice, int maxPrice, ProductCategory category){
    return null;
    }
}