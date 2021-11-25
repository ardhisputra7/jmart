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

@RestController
@RequestMapping("/coupon")
public class CouponController implements BasicGetController<Coupon> {

    @JsonAutowired(filepath = "C:/Java/Jmart/resource", value = Coupon.class)
    public static JsonTable<Coupon> couponTable;

    @GetMapping("/{id}/canApply")
    boolean canApply (@PathVariable int id, @RequestParam double price, @RequestParam double discount) {
        Predicate<Coupon> couponPredicate = applicable -> applicable.id == id;
        return Objects.requireNonNull(Algorithm.find(getJsonTable(), couponPredicate)).canApply(price,discount);
    }

    @GetMapping("/getAvailable")
    List<Coupon> getAvailable (@RequestParam int page,@RequestParam int pageSize){
        Predicate<Coupon> couponPredicate = available -> available.isUsed();
        return Algorithm.paginate(getJsonTable(), page, pageSize, couponPredicate);
    }

    @Override
    public JsonTable<Coupon> getJsonTable() {
        return couponTable;
    }

    @GetMapping("/{id}/isUsed")
    boolean isUsed (@PathVariable int id) {
        for (Coupon coupon : couponTable){
            if(coupon.id == id)
                return coupon.isUsed();
        }
        return false;
    }
}
