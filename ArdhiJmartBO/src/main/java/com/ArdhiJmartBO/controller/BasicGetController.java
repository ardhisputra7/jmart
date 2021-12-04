package com.ArdhiJmartBO.controller;
import com.ArdhiJmartBO.Algorithm;
import com.ArdhiJmartBO.Predicate;
import com.ArdhiJmartBO.dbjson.Serializable;
import com.ArdhiJmartBO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
public interface  BasicGetController <T extends  Serializable>{

    public abstract JsonTable<T> getJsonTable();

//    @GetMapping("/{id}")
//    default T getById(@PathVariable int id){
//        return Algorithm.<T>find(getJsonTable(), (e) -> e.id == id);
//    }
//
//    @GetMapping("/page")
//    default List<T> getPage (@RequestParam int page, @RequestParam int pageSize){
//        return (List<T>) Algorithm.<T>paginate((Iterator<T>) getJsonTable(), page, pageSize, (e) -> true);
//    }

    @GetMapping("/page")
    public default List<T> getPage(@RequestParam int page, @RequestParam int pageSize) {
        Predicate<T> pred = element -> true;
        List<T> list = Algorithm.collect(getJsonTable(), pred);
        return Algorithm.<T>paginate(list, page, pageSize, pred);
    }

    @GetMapping("/{id}")
    public default T getById(@PathVariable int id){
        Predicate<T> pred = element -> element.id == id;
        return Algorithm.find(getJsonTable().iterator(), pred);
    }

}

