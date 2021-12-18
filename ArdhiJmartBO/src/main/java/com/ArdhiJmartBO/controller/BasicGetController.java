package com.ArdhiJmartBO.controller;
import com.ArdhiJmartBO.Algorithm;
import com.ArdhiJmartBO.Predicate;
import com.ArdhiJmartBO.dbjson.Serializable;
import com.ArdhiJmartBO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

/**
 * Controller class basic
 * Menggunakan Spring framework
 * @author Ardhi Putra
 * @param <T> mengextend Serializable
 */
@RestController
public interface  BasicGetController <T extends  Serializable>{

    /**
     * abstract method untuk JsonTable
     * @return mengembalikan JsonTable
     */
    public abstract JsonTable<T> getJsonTable();

    /**
     * Method untuk mendapat page
     * @param page page yang ingin diproses
     * @param pageSize jumlah item dalam tiap page
     * @return mereturn paginate
     */
    @GetMapping("/page")
    public default List<T> getPage(@RequestParam int page, @RequestParam int pageSize) {
        Predicate<T> pred = element -> true;
        List<T> list = Algorithm.collect(getJsonTable(), pred);
        return Algorithm.<T>paginate(list, page, pageSize, pred);
    }

    /**
     * method untuk mengembalikan sesuai degan id
     * @param id id dari yang ingin getByID
     * @return mereturn item sesuai dengan id
     */
    @GetMapping("/{id}")
    public default T getById(@PathVariable int id){
        Predicate<T> pred = element -> element.id == id;
        return Algorithm.find(getJsonTable().iterator(), pred);
    }

}

