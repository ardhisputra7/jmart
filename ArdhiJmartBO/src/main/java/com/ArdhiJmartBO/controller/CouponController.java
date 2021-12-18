package com.ArdhiJmartBO.controller;

import com.ArdhiJmartBO.Algorithm;
import com.ArdhiJmartBO.Coupon;
import com.ArdhiJmartBO.Predicate;
import com.ArdhiJmartBO.Product;
import com.ArdhiJmartBO.dbjson.JsonAutowired;
import com.ArdhiJmartBO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Controller class untuk coupon
 * Menggunakan Spring framework
 * Mengimplementasikan BasicGetController
 * @author Ardhi Putra
 *
 */
@RestController
@RequestMapping("/coupon")
public class CouponController implements BasicGetController<Coupon> {

    @JsonAutowired(filepath = "C:/Java/Jmart/resource/coupon.json", value = Coupon.class)
    public static JsonTable<Coupon> couponTable;

    /**
     *Method untuk melakukan pemasangan coupon
     * @param id id dari coupon
     * @param price harga dari product
     * @param discount besar discount
     * @return mengembalikan coupon yang bisa dipasangkan
     */
    @GetMapping("/{id}/canApply")
    boolean canApply (@PathVariable int id, @RequestParam double price, @RequestParam double discount) {
        Predicate<Coupon> couponPredicate = applicable -> applicable.id == id;
        return Objects.requireNonNull(Algorithm.find(getJsonTable(), couponPredicate)).canApply(price,discount);
    }

    /**
     *Method untuk menentukan coupon yang available
     * @param page page dari coupon
     * @param pageSize jumlah coupon tiap halaman
     * @return mengembalikan halaman yang berisi coupon
     */
    @GetMapping("/getAvailable")
    List<Coupon> getAvailable (@RequestParam int page,@RequestParam int pageSize){
        Predicate<Coupon> couponPredicate = available -> available.isUsed();
        return Algorithm.paginate(getJsonTable(), page, pageSize, couponPredicate);
    }

    /**
     * method untuk mengembalikan jsonTable dari coupon
     * @return couponTable
     */
    @Override
    public JsonTable<Coupon> getJsonTable() {
        return couponTable;
    }

    /**
     *Method untuk menentukan apakah coupun sudah digunakan
     * @param id id dari coupun
     * @return mengembalikan coupon sudah digunakan atau false bila belum
     */
    @GetMapping("/{id}/isUsed")
    boolean isUsed (@PathVariable int id) {
        for (Coupon coupon : couponTable){
            if(coupon.id == id)
                return coupon.isUsed();
        }
        return false;
    }
}
